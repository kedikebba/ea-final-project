package ea.finalproject.serviceprovider.model;

import ea.finalproject.serviceprovider.repository.ServiceProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plans {

    @Autowired
    private ServiceProviderRepo provider;

    private List<Plan> plans;

    public Plans(){
        plans = new ArrayList<>(
                Arrays.asList(
                        new Plan("100", "A100","online offer", 25.00, "monthly", "prepaid talk", provider.getServiceProviderByProviderId("100")),
                        new Plan("101", "A101","limited time offer", 45.00, "monthly", "prepaid data", provider.getServiceProviderByProviderId("101"))
                )
        );
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
}
