package kafka_exmple.kafka.controller;

import kafka_exmple.kafka.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;

    // Endpoint to send XML data
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public User createUser(@RequestBody User user) {
        // Simulate saving the user
        System.out.println("Received User: " + user);

        // Return the user object as XML
        return user;
    }

    // Endpoint to get a sample user in XML
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public User getUser() {

        // Sample User
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        userKafkaTemplate.send("topic1", user);

        return user;
    }
}
