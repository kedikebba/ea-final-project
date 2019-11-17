package ea.finalProject.paymentService.service.implementation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ea.finalProject.paymentService.model.Payment;
import ea.finalProject.paymentService.model.PaymentType;
import ea.finalProject.paymentService.model.PaymentTypeBuilder;
import ea.finalProject.paymentService.service.PaymentService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDate;
import java.util.*;

@Service
public class PaymentServiceImp implements PaymentService {


    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";


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
        PaymentType paymentType = new PaymentType();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> retMap = mapper.readValue(json, HashMap.class);

        if (retMap.get("paymentType") == "bank") {
            paymentType = new PaymentTypeBuilder()
                    .setAccountNumber(retMap.get("accountNumber"))
                    .setBankName(retMap.get("bankName"))
                    .setName(retMap.get("name"))
                    .buildPaymentType();
        }else if(retMap.get("paymentType") == "creditcard") {
            paymentType = new PaymentTypeBuilder()
                    .setAccountNumber(retMap.get("creditcardNumber"))
                    .setBankName(retMap.get("cvv"))
                    .setName(retMap.get("name"))
                    .setName(retMap.get("expiryDate"))
                    .buildPaymentType();

        }else if(retMap.get("paymentType") == "payl") {
            paymentType = new PaymentTypeBuilder()
                    .setAccountNumber(retMap.get("email"))
                    .setBankName(retMap.get("email"))
                    .buildPaymentType();

        }
        return paymentType;

    }
    @Override
    public Payment payment(String json, PaymentType paymentType) throws JsonProcessingException {

        Payment payment = new Payment();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> retMap = mapper.readValue(json, HashMap.class);

        payment.setFirstName(retMap.get("name"));
        payment.setLastName(retMap.get("name"));
        payment.setEmail(retMap.get("email"));
        payment.setPlan(retMap.get("plan"));
        payment.setServiceProvider(retMap.get("serviceProvider"));
        payment.setAmount(Double.valueOf(retMap.get("amount")));
        payment.setSubscriptionDate(LocalDate.now());
        payment.setExpiryDate(LocalDate.now().plusDays(30));
        payment.setPaymentType(paymentType);

        return payment;
    }


    public  String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
//            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public  String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

//            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
