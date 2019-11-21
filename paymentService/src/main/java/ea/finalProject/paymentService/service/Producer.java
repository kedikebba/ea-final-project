package ea.finalProject.paymentService.service;


import ea.finalProject.paymentService.model.PaymentDetails;
import ea.finalProject.paymentService.model.PaymentWrapper;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer {


    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "Subscription";


    @Autowired
    private KafkaTemplate<String, PaymentWrapper> kafkaTemplate;

    public void sendMessage(PaymentWrapper paymentDetails) {
        logger.info(String.format("$$ -> Producing message --> %s", paymentDetails));
        this.kafkaTemplate.send(TOPIC, paymentDetails);

    }
}
