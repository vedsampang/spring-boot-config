package com.sampangs.springbootconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
public class GreetingController {

    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Value("${my.list.values}")
    private List<String> listValues;

    @Autowired
    private DbSettings dbSettings;

    @Autowired
    private Environment env;

    //@Value("#{${dbValues}}")
    //private Map<String, String> dbValues;

    public GreetingController(){
    }

    @GetMapping("/greeting")
    public String Greeting(){
        return greetingMessage + dbSettings.getConnection() + dbSettings.getHost();
    }

    @GetMapping("/envdetails")
    public String envDetails(){
        return env.toString();
    }
}
