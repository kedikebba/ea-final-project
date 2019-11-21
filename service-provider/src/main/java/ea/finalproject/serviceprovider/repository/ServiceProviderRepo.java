package ea.finalproject.serviceprovider.repository;

import ea.finalproject.serviceprovider.model.Plan;
import ea.finalproject.serviceprovider.model.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderRepo extends MongoRepository<ServiceProvider, String> {
    public List<ServiceProvider> getServiceProviderByProviderCountry(String country);

    public ServiceProvider getServiceProviderByProviderId(String id);

    public ServiceProvider getServiceProviderByProviderCode(String codeName);

    public void deleteServiceProviderByProviderCode(String id);



}
