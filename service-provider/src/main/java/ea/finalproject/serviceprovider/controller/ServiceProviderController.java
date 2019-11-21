package ea.finalproject.serviceprovider.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import ea.finalproject.serviceprovider.model.ServiceProvider;
import ea.finalproject.serviceprovider.model.ServiceProviders;
import ea.finalproject.serviceprovider.repository.PlansRepository;
import ea.finalproject.serviceprovider.repository.ServiceProviderRepo;
import ea.finalproject.serviceprovider.model.Plan;
import ea.finalproject.serviceprovider.service.serviceimpl.TokenDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/provider")
public class ServiceProviderController {

    @Autowired
    private TokenDecoder tokenDecoder;

    @Value("{SECRET_KEY:#{null}}")
    private String SECRET_KEY;

    @Autowired
    private ServiceProviderRepo serviceProviderRepo;

    @Autowired
    private PlansRepository plansRepository;

    //private static final Logger log = LoggerFactory.getLogger(ServiceProviderController.class);

    @GetMapping("/list")
    public List<ServiceProvider> listProvider() {

        return serviceProviderRepo.findAll();
    }

    @GetMapping("/{codeName}")
    public ServiceProvider getProvider(/*@RequestHeader(name = "Authorization") String token,*/ @PathVariable String codeName) throws UnsupportedEncodingException, JsonProcessingException {
//        HashMap<String, String> data = tokenDecoder.decode(token);
//        if (data.get("role").equals("ROLE_USER")) {
//            //log.info("retrieving service providers");
//            return serviceProviderRepo.getServiceProviderByProviderCode(codeName);
//        }
        return serviceProviderRepo.getServiceProviderByProviderCode(codeName);
    }

    @GetMapping("/list/{country}")
    public List<ServiceProvider> getProvidersByCountry(@PathVariable String country) {
        //log.info("retrieving service providers by countries");
        return serviceProviderRepo.getServiceProviderByProviderCountry(country);
    }

    @GetMapping("/{providerCode}/plans")
    public List<Plan> getPlans(@PathVariable String providerCode) throws SignatureException {
        List<Plan> providerPlans = plansRepository.getPlansByProviderProviderId(providerCode);
        return providerPlans;
    }

    @PostMapping("/save/all")
    public String addPlans() {
        serviceProviderRepo.deleteAll();
        ServiceProviders providers = new ServiceProviders();
        try {
            serviceProviderRepo.saveAll(providers.getServiceProviders());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Failed to save providers";
        }
        return "Providers saved successfully";
    }

    @PostMapping("/save")
    public String addProvider(@RequestBody ServiceProvider provider) {
        try {
            serviceProviderRepo.save(provider);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Failed to add";
        }
        return "Provider added";
    }

    @DeleteMapping("/delete/{codeName}")
    public String delete(@PathVariable String codeName) {
        serviceProviderRepo.deleteServiceProviderByProviderCode(codeName);
        return "Provider deleted";
    }
}
