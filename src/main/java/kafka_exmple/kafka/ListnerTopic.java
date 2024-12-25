package kafka_exmple.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ListnerTopic {

    @Autowired
    private KafkaTemplate<String, User> userTemplate;

    @PostMapping("/send/{topicName}")
    public void sendMessage(@RequestBody User user, @PathVariable String topicName) {
        log.info("Sending : {}", user);
        log.info("--------------------------------");

        userTemplate.send(topicName, user);
    }

    @KafkaListener(id = "myId", topics = "topic1", containerFactory = "userKafkaListenerContainerFactory")
    public void listen(User user) {
        log.info("Received XML message: :{} ", user);
    }
//
//    @KafkaListener(topics = "topic1", id = "id", containerFactory = "userKafkaListenerContainerFactory")
//    public void listen(User user) {
//        System.out.println("Received XML Message: " + user);
//    }
}
