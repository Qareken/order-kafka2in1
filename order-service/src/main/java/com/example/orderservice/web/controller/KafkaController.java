package com.example.orderservice.web.controller;


import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderEvent;
import com.example.orderservice.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kafka")
public class KafkaController {
    @Value("${app.kafka.kafkaMessageTopic}")
    private String topicName;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final KafkaMessageService kafkaMessageService;
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Order order){
        System.out.println(order.getProduct() + " "+ order.getQuantity());
        kafkaTemplate.send(topicName, new OrderEvent(order.getProduct(), order.getQuantity()));
        return ResponseEntity.ok("Message sent to kafka");
    }
    @GetMapping("/{name}")
    public ResponseEntity<OrderEvent> getById(@PathVariable String name){
        return ResponseEntity.ok(kafkaMessageService.getById(name).orElseThrow());
    }
}
