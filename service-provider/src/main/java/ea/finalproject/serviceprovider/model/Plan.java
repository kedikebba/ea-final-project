package ea.finalproject.serviceprovider.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plans")
@Data
public class Plan {
    @Id
    private String id;
    private String planCode;
    private String planName;
    private double price;
    private String time;
    private String category;

    private ServiceProvider provider;

    public Plan(String planCode, String planName, double price, String time, String category) {
        this.planCode = planCode;
        this.planName = planName;
        this.price = price;
        this.time = time;
        this.category = category;
    }
}
