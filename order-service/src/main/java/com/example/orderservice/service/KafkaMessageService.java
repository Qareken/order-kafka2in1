package com.example.orderservice.service;


import com.example.orderservice.model.OrderEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KafkaMessageService {
    private final List<OrderEvent> messages = new ArrayList<>();
    public void  add(OrderEvent message){
        messages.add(message);
    }
    public Optional<OrderEvent> getById(String name){
        return messages.stream().filter(it->it.getProduct().equals(name)).findFirst();
    }
}
