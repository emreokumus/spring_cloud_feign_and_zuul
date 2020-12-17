package com.emreokumus.eurekaclient.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Qualifier("eurekaClient")
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/greeting/{name}")
    public String greeting(@PathVariable("name") String name) {
        Application application
                = eurekaClient.getApplication("spring-cloud-eureka-client");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String appName = instanceInfo.getAppName();
        String hostName = instanceInfo.getHostName();
        int port = instanceInfo.getPort();
        //return String.format("Hello from '%s' by '%s'!", eurekaClient.getApplication(appName).getName(),name);
        return String.format("Hello from '%s' host: '%s' port:'%s' by '%s'!",appName,hostName,port,name);
    }
}
