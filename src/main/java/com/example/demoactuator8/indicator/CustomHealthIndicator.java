package com.example.demoactuator8.indicator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component()
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder bldr) throws Exception {
        // TODO implement some check
        boolean running = true;
        if (running) {
            bldr.up();
        } else {
            bldr.down();
        }
    }
}