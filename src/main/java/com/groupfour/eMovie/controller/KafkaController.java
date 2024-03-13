package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.VoucherOrder;
import com.groupfour.eMovie.service.VoucherOrderProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Value("${kafka.topic.voucher-order}")
    private String voucherOrderTopic;

    @Value("${kafka.topic.my-topic}")
    private String myTopic;

    private final VoucherOrderProducerService voucherOrderProducerService;

    private AtomicLong atomicLong = new AtomicLong();

    public KafkaController(VoucherOrderProducerService voucherOrderProducerService) {
        this.voucherOrderProducerService = voucherOrderProducerService;
    }

    @PostMapping
    public void sendMessageToKafkaTopic(@RequestParam("name") String name) {
        voucherOrderProducerService.sendMessage(myTopic, VoucherOrder.builder()
                .userId(1)
                .createTime(LocalDateTime.now())
                .payTime(LocalDateTime.now())
                .lastModTime(LocalDateTime.now())
                .voucherId(222L)
                .status(1)
                .createTime(LocalDateTime.now())
                .build());
        voucherOrderProducerService.sendMessage(voucherOrderTopic, VoucherOrder.builder()
                .userId(2)
                .createTime(LocalDateTime.now())
                .payTime(LocalDateTime.now())
                .lastModTime(LocalDateTime.now())
                .voucherId(333L)
                .status(1)
                .createTime(LocalDateTime.now())
                .build());
    }
}
