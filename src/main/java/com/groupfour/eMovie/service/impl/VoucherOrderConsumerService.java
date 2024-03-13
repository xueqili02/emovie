package com.groupfour.eMovie.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface VoucherOrderConsumerService {

    void consumeMessage(ConsumerRecord<String, String> voucherOrderConsumerRecord);
}
