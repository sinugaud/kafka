package kafka_exmple.kafka;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class User {
    @JacksonXmlProperty(localName = "id")
    private int id;

    @JacksonXmlProperty(localName = "name")
    private String name;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}
