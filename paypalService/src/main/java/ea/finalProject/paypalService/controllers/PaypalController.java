package ea.finalProject.paypalService.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaypalController {

    @GetMapping("/paypal")
    public String payPaypal(){
        return "OK";
    }
}
