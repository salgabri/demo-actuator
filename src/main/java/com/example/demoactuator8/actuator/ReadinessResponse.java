package com.example.demoactuator8.actuator;

import lombok.Data;

@Data
public class ReadinessResponse {

    private String status;
    private double busyThreads;
    private double totalThreads;
    private double percentageThreads;
}
