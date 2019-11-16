package ea.finalProject.paymentService.service;


import ea.finalProject.paymentService.model.Payment;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class Producer {


    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "kedikebba";


    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    public void sendMessage(Payment payment) {
        logger.info(String.format("$$ -> Producing message --> %s", payment));
        this.kafkaTemplate.send(TOPIC, payment);

    }
}
