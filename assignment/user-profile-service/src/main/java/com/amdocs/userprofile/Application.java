package com.amdocs.userprofile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import com.amdocs.userprofile.events.models.UserProfileChangeModel;


@SpringBootApplication
@EnableBinding(Sink.class)
public class Application {
	
	 private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
/*  @StreamListener(Sink.INPUT)
  public void loggerSink(UserProfileChangeModel userProfileChange) {
        logger.info("Received an event for User Profile Change id {} and action {} and address {} and phonenumber {}", userProfileChange.getId(),userProfileChange.getAction(),userProfileChange.getAddress(),userProfileChange.getPhoneNumber());
    }
*/	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
