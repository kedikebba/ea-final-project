package ea.finalproject.uiservice.model;



import java.util.List;

public class ServiceProvider {
    private String providerId;
    private String providerName;
    private String providerCode;
    private String providerCountry;
    private List<Plan> plans;


    public ServiceProvider(String providerName, String providerCode, String providerCountry, List<Plan> plans) {
        this.providerName = providerName;
        this.providerCode = providerCode;
        this.providerCountry = providerCountry;
        this.plans = plans;
    }
}
