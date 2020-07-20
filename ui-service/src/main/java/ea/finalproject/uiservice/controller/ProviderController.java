package ea.finalproject.uiservice.controller;

import net.minidev.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import ea.finalproject.uiservice.model.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/svp")
public class ProviderController {

    @Value("{PROVIDER_SERVICE:#{null}}")
    private String providerSvc;

    @Autowired
    private RestTemplate template;

    private HttpHeaders headers;


    @GetMapping("/{country}")
    public ResponseEntity<List<ServiceProvider>> getCountryProvider(@PathVariable String country, @RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException, JsonProcessingException {
        JSONObject request = new JSONObject();
        request.put("Authorization",token);
        headers = new HttpHeaders();
        //headers.set("Authorization",token);
        headers.add("Authorization","Bearer "+token);
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Content-Type","application/json");
        //HttpEntity<JSONObject> entity = new HttpEntity<>(request, headers);
        HttpEntity<JSONObject> entity = new HttpEntity<>(request);
        final String uri = String.format("http://%s/user/register",providerSvc);
        try {
            //List<ServiceProvider> providers = template.exchange(uri, HttpMethod.GET, entity, ServiceProvider.class);#
            ResponseEntity<List<ServiceProvider>> response = template.exchange(uri, HttpMethod.POST, entity,
                    new ParameterizedTypeReference<List<ServiceProvider>>() {
                    });
            return response;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public String getProvidersFromCountry(){
        return "";
    }

}
