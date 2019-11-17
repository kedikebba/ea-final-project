package ea.finalProject.paymentService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ea.finalProject.paymentService.model.Payment;
import ea.finalProject.paymentService.model.PaymentType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

public interface PaymentService {

    public Map<String, Object> toMap(JSONObject object) throws JSONException;

    public List<Object> toList(JSONArray array) throws JSONException;

    public PaymentType paymentType(String json) throws JsonProcessingException;

    public Payment payment(String json, PaymentType paymentType) throws JsonProcessingException;

    public  String encrypt(String value);

    public  String decrypt(String value);
}
