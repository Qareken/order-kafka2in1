package com.example.orderservicestatus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class StatusEvent {
    private String status;
    private Instant date;
}
