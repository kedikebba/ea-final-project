package ea.finalProject.paymentService.repository;


import ea.finalProject.paymentService.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    public Payment findPaymentByAmountEquals(Double value);
    public Payment getFirstByAmountEquals(Double value);

//    public  List<Payment> findAllByPaymentType(String paymentType);



}
