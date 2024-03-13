package com.groupfour.eMovie.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * kafka configuration
 *
 * @author lixq
 */
@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.voucher-order}")
    private String voucherOrderTopic;

    @Value("${kafka.topic.my-topic}")
    private String myTopic;

    /**
     * JSON消息转换器
     */
    @Bean
    public RecordMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    /**
     * 通过注入一个 NewTopic 类型的 Bean 来创建 topic，如果 topic 已存在，则会忽略。
     */
    @Bean
    public NewTopic voucherOrderTopic() {
        return new NewTopic(voucherOrderTopic, 3, (short) 1);
    }

    @Bean
    public NewTopic myTopic() {
        return new NewTopic(myTopic, 2, (short) 1);
    }
}
