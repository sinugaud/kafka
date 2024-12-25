package kafka_exmple.kafka.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class XmlDeserializer<T> implements Deserializer<T> {
    private final XmlMapper xmlMapper = new XmlMapper();
    private Class<T> targetType;

    public XmlDeserializer(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return data == null ? null : xmlMapper.readValue(data, targetType);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing XML", e);
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
