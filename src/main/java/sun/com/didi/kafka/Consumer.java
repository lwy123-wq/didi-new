package sun.com.didi.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

}
