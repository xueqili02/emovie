package com.groupfour.eMovie.service;

import org.apache.kafka.common.internals.Topic;
import org.springframework.stereotype.Service;

public interface VoucherOrderProducerService {

    /**
     * 向Kafka发送订单消息
     *
     * @param topic Topic名称
     * @param o     对象
     */
    void sendMessage(String topic, Object o);
}
