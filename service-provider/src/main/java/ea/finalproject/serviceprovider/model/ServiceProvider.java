package ea.finalproject.serviceprovider.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "providers")
public class ServiceProvider {
    @Id
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
