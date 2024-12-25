package kafka_exmple.kafka.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class XmlSerializer<T> implements Serializer<T> {
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public byte[] serialize(String topic, T data) {
        try {
            return data == null ? null : xmlMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing XML", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No special configuration
    }

    @Override
    public void close() {
        // No special cleanup
    }
}
