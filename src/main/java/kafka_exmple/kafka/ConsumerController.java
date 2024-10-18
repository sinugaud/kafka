package kafka_exmple.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ConsumerController {


    @KafkaListener(topics = "topic-1", groupId = "group1")
    void listener(User data) {
        log.info("Received message [{}] in group1", data);

    }
}
