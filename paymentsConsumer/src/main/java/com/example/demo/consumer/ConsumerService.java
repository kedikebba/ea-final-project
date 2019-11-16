package com.example.demo.consumer;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "kedikebba", groupId = "group_id")
    public void consume(String payment){
        Gson gson = new Gson();
        Payment paymentObject = gson.fromJson(payment, Payment.class);
        logger.info(String.format("$$ -> Consumed Message -> %s",paymentObject));
    }
}
