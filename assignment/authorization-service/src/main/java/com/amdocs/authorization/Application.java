package com.amdocs.authorization;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.net.Proxy;
import java.net.InetSocketAddress;


@SpringBootApplication
@EnableBinding(Source.class)
public class Application {
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
