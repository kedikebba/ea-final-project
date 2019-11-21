package ea.finalProject.bankService.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    @GetMapping("bank")
    public String payBank(){

        return "OK";
    }
}
