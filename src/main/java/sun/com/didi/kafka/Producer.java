package sun.com.didi.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;
import sun.com.didi.entity.Recruit;

@Component
public class Producer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Recruit producer(Recruit recruit){
        kafkaTemplate.send("kafka.topic",recruit).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送失败");
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("发送成功");
            }
        });
        return recruit;
    }
}
