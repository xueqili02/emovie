package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.service.VoucherOrderProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class VoucherOrderProducerServiceImpl implements VoucherOrderProducerService {


    private final KafkaTemplate<String, Object> kafkaTemplate;

    public VoucherOrderProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, Object o) {
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, null, System.currentTimeMillis(), String.valueOf(o.hashCode()), o);
        // 异步发送
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(producerRecord);
        // 任务完成，异步回调
        future.whenComplete((sendResult, throwable) -> {
            // 处理无异常和有异常的情况
            if (throwable == null) {
                log.info("生产者成功发送消息到" + sendResult.getProducerRecord().topic() + "-> " + sendResult.getProducerRecord().value().toString());
            } else {
                log.error("生产者发送消失败，原因：{}", throwable.getMessage());
            }
        });
    }
}
