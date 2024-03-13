package com.groupfour.eMovie.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.groupfour.eMovie.entity.VoucherOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VoucherOrderConsumerServiceImpl implements VoucherOrderConsumerService {

    @Value("${kafka.topic.voucher-order}")
    private String voucherOrderTopic;

    @Value("${kafka.topic.my-topic}")
    private String myTopic;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @KafkaListener(topics = {"${kafka.topic.voucher-order}"}, groupId = "group1")
    public void consumeMessage(ConsumerRecord<String, String> voucherOrderConsumerRecord) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            VoucherOrder voucherOrder = objectMapper.readValue(voucherOrderConsumerRecord.value(), VoucherOrder.class);
            log.info("消费者消费topic:{} partition:{}的消息 -> {}", voucherOrderConsumerRecord.topic(), voucherOrderConsumerRecord.partition(), voucherOrder.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
