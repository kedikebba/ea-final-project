package ea.finalproject.uiservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.finalproject.uiservice.model.LoginRequest;
import ea.finalproject.uiservice.model.User;
import ea.finalproject.uiservice.service.TokenDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@SessionAttributes("token")
@Controller
public class MainController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TokenDecoderService tokenDecoderService;

    @GetMapping({"/","/index"})
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(@ModelAttribute LoginRequest loginRequest){
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@Valid @ModelAttribute LoginRequest loginRequest, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "login";
        }
        final String uri = "http://34.70.86.26/user/login";
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(loginRequest),
                new ParameterizedTypeReference<String>() {
                });
        if(response.getStatusCode().is2xxSuccessful())
        {
            System.out.println(response.getBody());
            model.addAttribute("token", response.getBody());
        }
        return "index";
    }

    @GetMapping("/signup")
    public String registerPage(@ModelAttribute User user){
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "signup";
        }
        final String uri = "http://34.70.86.26/user/register";
        restTemplate.postForLocation(uri, user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) throws UnsupportedEncodingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderService.decode((String) model.getAttribute("token"));

        final String uri = "http://34.70.86.26/user/account/"+dataHash.get("username");
        try {
            ResponseEntity<User> response = restTemplate.exchange(uri, HttpMethod.GET, null,
                    new ParameterizedTypeReference<User>() {
                    });
            if (response.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("user", response.getBody());
                return "profile";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "login";
    }
}
