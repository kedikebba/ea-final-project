package ea.finalProject.paymentService.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ea.finalProject.paymentService.model.Payment;
import ea.finalProject.paymentService.repository.PaymentRepository;
import ea.finalProject.paymentService.service.PaymentService;
import ea.finalProject.paymentService.service.Producer;
import ea.finalProject.paymentService.service.implementation.TokenDecoderServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;


@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private Payment payment;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private final Producer producer;

    @Autowired
    private TokenDecoderServiceImp tokenDecoderServiceImp;


    public PaymentsController(Producer producer) {
        this.producer = producer;
    }




    @GetMapping("/all")
    public String getAll(){
        return paymentRepository.findAll().toString();
    }
    @GetMapping("/test")
    public void test(){
        payment = paymentRepository.getFirstByAmountEquals(1000.0);
        System.out.print(payment.toString());
        this.producer.sendMessage(payment);
    }

    @GetMapping("/pay")
    public String pay( @RequestHeader (name="Authorization") String token ) throws UnsupportedEncodingException, JsonProcessingException, JsonProcessingException {
        HashMap<String, String> dataHash= tokenDecoderServiceImp.decode(token);
        if(dataHash.get("role").equals("ROLE_USER")) {

            return "Authorized";
        }
        return "Unauthorised";
    }

    @HystrixCommand(fallbackMethod = "reliable")
    @GetMapping("/hystrix")
    public String readingList() {
        String uri = "http://localhost:8090/recommended";

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Sorry, Request Could not be processed!";
    }

}
