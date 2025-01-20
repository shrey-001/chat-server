package com.example.demo.controllers;


import com.example.demo.services.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/producer")
    public void sendDemoProducer() {
        //kafkaProducerService.sendDemoProducer("Hello Kafka");
    }
}
