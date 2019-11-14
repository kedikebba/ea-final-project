package ea.finalproject.serviceprovider.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "providers")
public class ServiceProvider {
    @Id
    private String providerId;
    private String providerName;
    private String providerCode;
    private String providerCountry;

}
