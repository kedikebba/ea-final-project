package ea.finalproject.uiservice.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ea.finalproject.uiservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class PaymentController {
    @Autowired
    RestTemplate restTemplate;

    private String token;

   // private String uri = "http://34.70.20.44/pay";
//   private String uri = "http://34.70.20.44/payAll";

    private  String uri = "http://34.70.20.44/payAll";

    private  PaymentWrapper paymentWrapper;


    @GetMapping("/pay")
    public String makePayment(@ModelAttribute(name = "paywrapper") PaymentWrapper wrapper, Model model, @SessionAttribute("token") String tokenString){
        paymentWrapper = wrapper;
        //paymentWrapper = new PaymentWrapper("MTN", "unlimited","monthly", 2000.0);
        model.addAttribute("bankAccount", new BankAccount());
        model.addAttribute("payPal", new PayPal());
        model.addAttribute("creditCard", new CreditCard());
        token = tokenString;
        return "paymentOption";
    }



    @HystrixCommand(fallbackMethod = "reliable")
    @PostMapping("/bankAccount")
    public String processPaymentBa(@ModelAttribute BankAccount bankAccount){

            PaymentDetailsWrapper paymentDetailsWrapper = createPaymentWrapper(paymentWrapper);
            paymentDetailsWrapper.setPaymentType("bank");
            paymentDetailsWrapper.setAccountNumber(bankAccount.getAccountNumber());
            paymentDetailsWrapper.setBankName(bankAccount.getBankName());

            System.out.println(paymentDetailsWrapper);


            ResponseEntity<String>  r = callPaymentService(token, paymentDetailsWrapper, uri);
            String s = String.valueOf(r);
            if(r.getBody().equals("OK")){
                return "redirect:/success";
            }
            return "Request not processsed!";
    }

    @HystrixCommand(fallbackMethod = "reliable")
    @PostMapping("/payPal")
    public String processPaymentPp(@ModelAttribute PayPal payPal){
        PaymentDetailsWrapper paymentDetailsWrapper = createPaymentWrapper(paymentWrapper);
        paymentDetailsWrapper.setEmail(payPal.getEmail());
        paymentDetailsWrapper.setPassword(payPal.getPassword());
        paymentDetailsWrapper.setPaymentType("paypal");
        ResponseEntity<String>  r = callPaymentService(token, paymentDetailsWrapper, uri);
        String s = String.valueOf(r);
        if(r.getBody().equals("OK")){
            return "redirect:/success";
        }
        return "Request not processsed!";
    }

    @HystrixCommand(fallbackMethod = "reliable")
    @PostMapping("/creditCard")
    public String processPaymentCc(@ModelAttribute CreditCard creditCard){
        PaymentDetailsWrapper paymentDetailsWrapper = createPaymentWrapper(paymentWrapper);
        paymentDetailsWrapper.setPaymentType("creditcard");
        paymentDetailsWrapper.setCreditcardNumber(creditCard.getCardNumber());
        paymentDetailsWrapper.setCvv(creditCard.getCvv());
        paymentDetailsWrapper.setName(creditCard.getCardName());
        paymentDetailsWrapper.setCardExpiryDate(creditCard.getExpiryDate());

        ResponseEntity<String>  r = callPaymentService(token, paymentDetailsWrapper, uri);
        String s = String.valueOf(r);
        if(r.getBody().equals("OK")){
            return "redirect:/success";
        }
        return "Request not processsed!";
    }


    private PaymentDetailsWrapper createPaymentWrapper(PaymentWrapper paymentWrapper){
        PaymentDetailsWrapper paymentDetailsWrapper = new PaymentDetailsWrapper();
        paymentDetailsWrapper.setPlan(paymentWrapper.getPlanName());
        paymentDetailsWrapper.setServiceProvider(paymentWrapper.getServiceProvider());
        paymentDetailsWrapper.setPeriod(paymentWrapper.getTime());
        paymentDetailsWrapper.setAmount(paymentWrapper.getPrice());
        return paymentDetailsWrapper;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    private ResponseEntity<String> callPaymentService(String token, PaymentDetailsWrapper paymentDetailsWrapper, String uri) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", "Bearer "+token);
        headers.add("Content-Type", "application/json");
        HttpEntity<Object> formEntity = new HttpEntity<Object>(paymentDetailsWrapper, headers);
        ResponseEntity<String> response = null;
        try{
            response = restTemplate.exchange(uri, HttpMethod.POST, formEntity,
                    new ParameterizedTypeReference<String>() {
                    });
        }
        catch (Exception e){

            e.printStackTrace();
        }
        finally {
            return response;
        }
    }
    @GetMapping("/success")
    public String success(){
        return "successMessage";
    }

    public String reliable(){
        return "No response at this time";
    }
}
