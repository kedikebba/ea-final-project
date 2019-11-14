package ea.finalproject.serviceprovider.controller;

import ea.finalproject.serviceprovider.model.Plans;
import ea.finalproject.serviceprovider.model.ServiceProvider;
import ea.finalproject.serviceprovider.repository.PlansRepository;
import ea.finalproject.serviceprovider.repository.ServiceProviderRepo;
import ea.finalproject.serviceprovider.model.Plan;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/provider")
public class ServiceProviderController {

    @Value("{SECRET_KEY:#{null}}")
    private String SECRET_KEY;

    @Autowired
    private ServiceProviderRepo serviceProviderRepo;

    @Autowired
    private PlansRepository plansRepository;

    @GetMapping("/list")
    public List<ServiceProvider> listProvider(){
        return serviceProviderRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional getProvider(@RequestParam String id){
        return serviceProviderRepo.findById(id);
    }

    @GetMapping("/list/{country}")
    public List<ServiceProvider> getProvidersByCountry(@RequestParam String country){
        return serviceProviderRepo.getServiceProviderByProviderCountry(country);
    }

    @GetMapping("/{provider}/plans")
    public List<Plan> getPlans(@RequestHeader String token, @RequestParam String provider) throws SignatureException {
        Jws<Claims> jwt = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
        List<Plan> providerPlans = plansRepository.getPlansByProviderProviderId(provider);
        return providerPlans;
    }

    @PostMapping("/plans/add")
    public String addPlans(){
        Plans plans = new Plans();
        try{
            plansRepository.saveAll(plans.getPlans());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Failed to save plans";
        }
        return "Plans added";
    }

    @PostMapping("/save")
    public String addProvider(@RequestBody ServiceProvider provider){
        try{
            serviceProviderRepo.save(provider);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Failed to add";
        }
        return "Provider added";
    }

    @DeleteMapping("/deleted")
    public String delete(ServiceProvider provider){
        serviceProviderRepo.delete(provider);
        return "Provider deleted";
    }

}
