package ea.finalProject.paymentService.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ea.finalProject.paymentService.model.PaymentDetails;
import ea.finalProject.paymentService.model.PaymentType;
import ea.finalProject.paymentService.model.PaymentTypeBuilder;
import ea.finalProject.paymentService.model.PaymentWrapper;
import ea.finalProject.paymentService.service.PaymentService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;

@Service
public class PaymentServiceImp implements PaymentService {

    private  SecretKeySpec secretKey;
    private  byte[] key;

    @Override
    public Map<String, Object> toMap(JSONObject object)  throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    @Override
    public List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    @Override
    public PaymentType paymentType(String json) throws JsonProcessingException {
        PaymentType paymentType = null;
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> retMap = mapper.readValue(json, HashMap.class);

        if (retMap.get("paymentType").equals("bank")) {

             paymentType = new PaymentTypeBuilder()
                    .setAccountNumber(retMap.get("accountNumber"))
                    .setBankName(retMap.get("bankName"))
                    .setName(retMap.get("name"))
                    .buildPaymentType();

        }else if(retMap.get("paymentType").equals("creditcard")) {
             paymentType = new PaymentTypeBuilder()

                    .setCreditcardNumber(Long.parseLong(String.valueOf(retMap.get("creditcardNumber"))))
                    .setCvv(Integer.parseInt(String.valueOf(retMap.get("cvv"))))
                    .setName(retMap.get("name"))
                    .setExpiryDate(retMap.get("expiryDate"))
                    .buildPaymentType();

        }else if(retMap.get("paymentType").equals("paypal")) {
             paymentType = new PaymentTypeBuilder()
                    .setAccountNumber(retMap.get("email"))
                    .setBankName(retMap.get("email"))
                    .buildPaymentType();

        }

        return paymentType;

    }
    @Override
    public PaymentDetails payment(String result, String json, String paymentType, String lastName, String firstName, String email) throws JsonProcessingException {

        PaymentDetails paymentDetails = new PaymentDetails();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> retMap = mapper.readValue(json, HashMap.class);

        paymentDetails.setFirstName(firstName);
        paymentDetails.setLastName(lastName);
        paymentDetails.setEmail(email);
        paymentDetails.setPlan(retMap.get("plan"));
        paymentDetails.setServiceProvider(retMap.get("serviceProvider"));
        Double d =  Double.parseDouble(String.valueOf(retMap.get("amount")));
        paymentDetails.setAmount(d);
        paymentDetails.setSubscriptionDate(LocalDate.now());
        paymentDetails.setExpiryDate(LocalDate.now().plusDays(30));
        paymentDetails.setPaymentType(paymentType);
        paymentDetails.setStatus(result);

        return paymentDetails;
    }


    //Encrypt and Decrypt Methods:


    @Value("${ENC_SECRET}")
    private String secret;

    public void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public  String decrypt(String strToDecrypt)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
//Create PaymentWrapper to be published to Kafka.
    @Override
    public PaymentWrapper paymentWrapper(String json, String firstName, String lastName, String email) throws JsonProcessingException {

        PaymentWrapper paymentWrapper = new PaymentWrapper();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> retMap = mapper.readValue(json, HashMap.class);

        paymentWrapper.setFirstName(firstName);
        paymentWrapper.setLastName(lastName);
        paymentWrapper.setEmail(email);
        paymentWrapper.setPlan(retMap.get("plan"));
        paymentWrapper.setServiceProvider(retMap.get("serviceProvider"));
        Double d =  Double.parseDouble(String.valueOf(retMap.get("amount")));
        paymentWrapper.setAmount(d);
        paymentWrapper.setPaymentType(retMap.get("paymentType"));
        paymentWrapper.setSubscriptionDate(LocalDate.now().toString());

        if(retMap.get("period").equals("daily")){
            paymentWrapper.setExpiryDate(LocalDate.now().plusDays(1).toString());
        }else{
            paymentWrapper.setExpiryDate(LocalDate.now().plusDays(30).toString());
        }

        return paymentWrapper;
    }

    @Override
    public HashMap<String, String> paymentOptions(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> retMap = mapper.readValue(json, HashMap.class);
        HashMap<String, String> paymentOptions = new HashMap<>();
        paymentOptions.put("paymentType", retMap.get("paymentType"));
        paymentOptions.put("bank", "bankService");
        paymentOptions.put("paypal", "paypalService");
        paymentOptions.put("creditcard", "creditCardService");

        return paymentOptions;
    }


}
