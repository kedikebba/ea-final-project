package ea.finalproject.serviceprovider.util;

import ea.finalproject.serviceprovider.model.Plan;
import lombok.Data;

import java.util.List;

@Data
public class ResponseTransfer {

    private String response;
    private List<Plan> plans;

    public ResponseTransfer(String response, List<Plan> plans) {

    }
}
