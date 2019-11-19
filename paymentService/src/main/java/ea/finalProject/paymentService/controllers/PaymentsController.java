package ea.finalProject.paymentService.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ea.finalProject.paymentService.model.PaymentDetails;
import ea.finalProject.paymentService.model.PaymentType;
import ea.finalProject.paymentService.model.PaymentWrapper;
import ea.finalProject.paymentService.repository.PaymentRepository;
import ea.finalProject.paymentService.repository.PaymentTypeRepo;
import ea.finalProject.paymentService.service.PaymentService;
import ea.finalProject.paymentService.service.Producer;
import ea.finalProject.paymentService.service.implementation.TokenDecoderServiceImp;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.*;


@RestController
//@RequestMapping("/payments")
public class PaymentsController {

    @Value("${CREDITCARD_SERVICE:#{null}}")
    private String creditCardService;

    @Value("${BANK_SERVICE:#{null}}")
    private String bankService;

    @Value("${PAYPAL_SERVICE:#{null}}")
    private String paypalService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private final Producer producer;

    @Autowired
    private TokenDecoderServiceImp tokenDecoderServiceImp;


    public PaymentsController(Producer producer) {
        this.producer = producer;
    }



    @PostMapping("/pay")
    @HystrixCommand(fallbackMethod = "fallBackMethod")
    public String pay( @RequestHeader (name="Authorization") String token,
                       @RequestBody String json ) throws UnsupportedEncodingException, JsonProcessingException, JsonProcessingException, JSONException {

        HashMap<String, String> dataHash= tokenDecoderServiceImp.decode(token);
        if(dataHash.get("role").equals("ROLE_USER")) {
          PaymentType paymentType =  paymentService.paymentType(json);

          //HashMap<String, String> paymentOptions = paymentService.paymentOptions(json);

          //String paymentOption = paymentOptions.get("paymentType");

          //String paymentEndpoint = paymentOptions.get(paymentOption);

          //System.out.println("\n\n\n\n\n\n\n"+String.format("http://%s/"+paymentOption,paymentEndpoint)+"\n\n\n\n\n\n");

          //final String result = restTemplate.getForObject(String.format("http://%s/"+paymentOption,paymentEndpoint), String.class);

            final String result = restTemplate.getForObject(String.format("http://%s/bank",bankService), String.class);

          String paymentTypeEncrypted = paymentService.encrypt(paymentType.toString());

          PaymentDetails paymentDetails = paymentService.payment(result, json, paymentTypeEncrypted);

          paymentRepository.save(paymentDetails);

          if(paymentDetails.getStatus().equals("OK")){
              PaymentWrapper paymentWrapper = paymentService.paymentWrapper(json);

              this.producer.sendMessage(paymentWrapper);
          }
            return "Payment Type:" + paymentType.toString() +"\n\n\n\n" +"Payment: "+ paymentDetails.toString() +"Payment Encrypted:" + paymentTypeEncrypted +"\n\n\n\n" ;
        }
        return "Unauthorised";
    }
    public String fallBackMethod(@RequestHeader (name="Authorization") String token,
                                 @RequestBody String json ) {

        return "Sorry, Request Could not be processed!";
    }

}
