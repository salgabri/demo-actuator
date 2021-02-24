package com.example.demoactuator8.actuator;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "readiness")
public class ReadinessActuator {

    @Autowired
    private MeterRegistry repo;

    @ReadOperation
    public WebEndpointResponse<Map> readiness() {
        Gauge busyThreads = repo.get("tomcat.threads.busy").gauge();
        Gauge totalThreads = repo.get("tomcat.threads.config.max").gauge();



        double busyThreadsCount = busyThreads.value();
        double allThreadsCount  = totalThreads.value();
        double maxThreadUsage = allThreadsCount * 0.6;
        double percentageUsage = busyThreadsCount / allThreadsCount;

        ReadinessResponse response = new ReadinessResponse();
        response.setBusyThreads(busyThreadsCount);
        response.setTotalThreads(allThreadsCount);
        response.setPercentageThreads(percentageUsage);

        WebEndpointResponse output;
        if ( busyThreadsCount > maxThreadUsage ) {
            response.setStatus("HELP");
            output = new WebEndpointResponse(response, WebEndpointResponse.STATUS_TOO_MANY_REQUESTS );
        }
        else {
            response.setStatus("OK");
            output = new WebEndpointResponse<>(response, WebEndpointResponse.STATUS_OK);
        }
        return output;
    }

}