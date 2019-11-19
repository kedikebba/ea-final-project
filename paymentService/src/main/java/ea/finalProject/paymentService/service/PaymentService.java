package ea.finalProject.paymentService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.finalProject.paymentService.model.PaymentDetails;
import ea.finalProject.paymentService.model.PaymentType;
import ea.finalProject.paymentService.model.PaymentWrapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public interface PaymentService {

    public Map<String, Object> toMap(JSONObject object) throws JSONException;

    public List<Object> toList(JSONArray array) throws JSONException;

    public PaymentType paymentType(String json) throws JsonProcessingException;

    public PaymentDetails payment(String result, String json, String paymentType) throws JsonProcessingException;
    public String encrypt(String strToEncrypt);
    public  String decrypt(String strToDecrypt);

    public PaymentWrapper paymentWrapper(String json) throws JsonProcessingException;

//    public  String encrypt(String value);
//
//    public  String decrypt(String value);
}
