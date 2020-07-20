package ea.finalproject.notificationservice.repository;

import ea.finalproject.notificationservice.Model.PaymentInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface PaymentInformationRepository extends MongoRepository<PaymentInformation, String> {
    List<PaymentInformation> findAllByExpiryDate(String localDate);
}
