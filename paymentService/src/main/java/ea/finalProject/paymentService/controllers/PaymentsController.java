package ea.finalProject.paymentService.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import ea.finalProject.paymentService.model.PaymentDetails;
import ea.finalProject.paymentService.model.PaymentType;
import ea.finalProject.paymentService.model.PaymentWrapper;
import ea.finalProject.paymentService.repository.PaymentRepository;
import ea.finalProject.paymentService.service.PaymentService;
import ea.finalProject.paymentService.service.Producer;
import ea.finalProject.paymentService.service.implementation.TokenDecoderServiceImp;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;


@RestController
public class PaymentsController {

    @Autowired
    private final Producer producer;
    @Value("${CREDITCARD_SERVICE:#{null}}")
    private String creditCardService;
    @Value("${BANK_SERVICE:#{null}}")
    private String bankService;
    @Value("${PAYPAL_SERVICE:#{null}}")
    private String paypalService;


    //For Local Use
//    private String paypalService = "localhost:8085";
//    private String bankService = "localhost:8083";
//    private String creditCardService = "localhost:8084";


    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TokenDecoderServiceImp tokenDecoderServiceImp;


    public PaymentsController(Producer producer) {
        this.producer = producer;
    }


    @PostMapping("/pay")
    @HystrixCommand(fallbackMethod = "fallBackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")})
    public String pay(@RequestHeader(name = "Authorization") String token,
                      @RequestBody String json) throws UnsupportedEncodingException, JsonProcessingException, JSONException {

        HashMap<String, String> dataHash = tokenDecoderServiceImp.decode(token);
        if (dataHash.get("role").equals("ROLE_USER")) {
            String firstName = dataHash.get("firstName");
            String lastName = dataHash.get("lastName");
            String email = dataHash.get("email");

            PaymentType paymentType = paymentService.paymentType(json);

            HashMap<String, String> paymentOptions = paymentService.paymentOptions(json);

            final String result = restTemplate.getForObject(String.format("http://%s/bank",bankService), String.class);

            String paymentTypeEncrypted = paymentService.encrypt(paymentType.toString());

            PaymentDetails paymentDetails = paymentService.payment(result, json, paymentTypeEncrypted, lastName, firstName, email);

            paymentRepository.save(paymentDetails);

            if (paymentDetails.getStatus().equals("OK")) {
                PaymentWrapper paymentWrapper = paymentService.paymentWrapper(json, firstName, lastName, email);
                System.out.println("\n\n\n\n"+paymentWrapper+"\n\n\n\n");
                this.producer.sendMessage(paymentWrapper);
            }
            return "OK";
        }
        return "Unauthorised";
    }

    @GetMapping("/testOne")
    public String testOne(){
        return "Your are Stupid!";
    }
    @PostMapping("/testTwo")
    public String testTwo(){
        return "Your are Stupid Two!";
    }



    @PostMapping("/payAll")
    public String payAll(@RequestHeader(name = "Authorization") String token,
                      @RequestBody String json) throws UnsupportedEncodingException, JsonProcessingException, JSONException {

        HashMap<String, String> dataHash = tokenDecoderServiceImp.decode(token);
        if (dataHash.get("role").equals("ROLE_USER")) {
            String firstName = dataHash.get("firstName");
            String lastName = dataHash.get("lastName");
            String email = dataHash.get("email");

            PaymentType paymentType = paymentService.paymentType(json);

            HashMap<String, String> paymentOptions = paymentService.paymentOptions(json);

            String paymentOption = paymentOptions.get("paymentType");
            String result;
            if (paymentOption.equals("bank")) {
                  result = restTemplate.getForObject(String.format("http://%s/bank",bankService), String.class);
            } else if (paymentOption.equals("creditcard")) {
                  result = restTemplate.getForObject(String.format("http://%s/creditcard",creditCardService), String.class);
            }else{
                result = restTemplate.getForObject(String.format("http://%s/paypal",paypalService), String.class);
            }

            String paymentTypeEncrypted = paymentService.encrypt(paymentType.toString());

            PaymentDetails paymentDetails = paymentService.payment(result, json, paymentTypeEncrypted, lastName, firstName, email);



            if (paymentDetails.getStatus().equals("OK")) {

                paymentRepository.save(paymentDetails);

                PaymentWrapper paymentWrapper = paymentService.paymentWrapper(json,firstName, lastName, email);
                System.out.println("\n\n\n\n"+paymentWrapper+"\n\n\n\n");

                this.producer.sendMessage(paymentWrapper);
            }
            return "OK";
        }
        return "Unauthorised";
    }

    public String fallBackMethod(@RequestHeader(name = "Authorization") String token,
                                 @RequestBody String json) {

        return "Sorry, Request Could not be processed!";
    }

}
