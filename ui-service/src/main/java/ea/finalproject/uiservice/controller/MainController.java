package ea.finalproject.uiservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/","/index"})
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }


}
