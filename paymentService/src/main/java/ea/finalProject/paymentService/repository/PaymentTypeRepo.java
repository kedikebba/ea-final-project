package ea.finalProject.paymentService.repository;

import ea.finalProject.paymentService.model.PaymentType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;


@Repository
public interface PaymentTypeRepo extends MongoRepository<PaymentType, String> {
}
