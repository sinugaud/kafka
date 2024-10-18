package kafka_exmple.kafka;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ListnerTopic {

    @Autowired
    private KafkaTemplate<Integer, User> userTemplate;
@PostMapping("/send/{topicName}")
    public void sendMessage(@RequestBody User user, @PathVariable String topicName) {
        log.info("Sending : {}", user);
        log.info("--------------------------------");

    userTemplate.send(topicName, user);
    }
}
