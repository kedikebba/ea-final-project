package ea.finalProject.paymentService.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ea.finalProject.paymentService.model.Payment;
import ea.finalProject.paymentService.model.PaymentType;
import ea.finalProject.paymentService.model.PaymentTypeBuilder;
import ea.finalProject.paymentService.repository.PaymentRepository;
import ea.finalProject.paymentService.repository.PaymentTypeRepo;
import ea.finalProject.paymentService.service.PaymentService;
import ea.finalProject.paymentService.service.Producer;
import ea.finalProject.paymentService.service.implementation.PaymentServiceImp;
import ea.finalProject.paymentService.service.implementation.TokenDecoderServiceImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.*;


@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentTypeRepo paymentTypeRepo;

    @Autowired
    private PaymentService paymentService;

//    @Autowired
//    private Payment payment;
//
//    @Autowired
//    private PaymentTy payment;

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
    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }
    @GetMapping("/test")
    public void test(){
        Payment payment = paymentRepository.getFirstByAmountEquals(1000.0);
        System.out.print(payment.toString());
        this.producer.sendMessage(payment);
    }

    @PostMapping("/pay")
    public String pay( @RequestHeader (name="Authorization") String token,
                       @RequestBody String json ) throws UnsupportedEncodingException, JsonProcessingException, JsonProcessingException, JSONException {

        HashMap<String, String> dataHash= tokenDecoderServiceImp.decode(token);

        if(dataHash.get("role").equals("ROLE_USER")) {

          PaymentType paymentType =  paymentService.paymentType(json);
          Payment payment = paymentService.payment(json, paymentType);
            paymentTypeRepo.save(paymentType);
            paymentRepository.save(payment);
            return "Payment Type:" + paymentType.toString() +"\n\n\n\n" +"Payment: "+ payment.toString();
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
