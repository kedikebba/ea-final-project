package ea.finalproject.serviceprovider.repository;

import ea.finalproject.serviceprovider.model.Plan;
import ea.finalproject.serviceprovider.model.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlansRepository extends MongoRepository<Plan, String> {
    public List<Plan> getPlansByProviderProviderId(String providerCode);
}
