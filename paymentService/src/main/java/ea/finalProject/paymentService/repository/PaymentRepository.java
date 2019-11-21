package ea.finalProject.paymentService.repository;


import ea.finalProject.paymentService.model.PaymentDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentDetails, String> {

    public PaymentDetails findPaymentByAmountEquals(Double value);
    public PaymentDetails getFirstByAmountEquals(Double value);

//    public  List<Payment> findAllByPaymentType(String paymentType);



}
