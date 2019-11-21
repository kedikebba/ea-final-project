package ea.finalproject.accountservice.service.serviceimpl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Service
public class TokenDecoderService {
    private ObjectMapper mapper = new ObjectMapper();
    public HashMap<String, String> decode(String token) throws UnsupportedEncodingException, JsonProcessingException {
        String payload = token.split("\\.")[1];
        String data = new String(Base64.decodeBase64(payload), "UTF-8");
        HashMap<String,String> dataHash = mapper.readValue(data, HashMap.class);
        return dataHash;
    }


}