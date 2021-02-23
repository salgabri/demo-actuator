package com.example.demoactuator8.controller;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActuatorController {

    @Autowired
    private MeterRegistry repo;

    @GetMapping(path = "threaduse")
    public ThreadView getThreadUse() {
        Gauge busyThreads = repo.get("tomcat.threads.busy").gauge();
        Gauge allThreads  = repo.get("tomcat.threads.config.max").gauge();

        double busy = busyThreads.value();
        double all = allThreads.value();
        double perc = busy/all;

        ThreadView view = new ThreadView();
        view.setBusy(busy);
        view.setTotal(all);
        view.setPercentage(perc);

        return view;
    }

}
