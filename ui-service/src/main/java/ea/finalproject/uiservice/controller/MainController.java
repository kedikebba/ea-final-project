package ea.finalproject.uiservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private RestTemplate template;

    @HystrixCommand(fallbackMethod = "reliable")
    @GetMapping({"/","/index"})
    public String showHomePage() {
        return "index";
    }
    @HystrixCommand(fallbackMethod = "reliable")
    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    public String reliable(){
        return "Ooops... Out of Service";
    }
}
