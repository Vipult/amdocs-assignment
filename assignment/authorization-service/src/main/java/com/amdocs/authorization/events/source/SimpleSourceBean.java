package com.amdocs.authorization.events.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.amdocs.authorization.events.models.UserProfileChangeModel;



@Component
public class SimpleSourceBean {
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishUserProfileChange (String action,Long id,String address,String phoneNumber) {
       logger.info("Sending Kafka message with action {},address{},phonenumber{} for id {}", action,address,phoneNumber,id);
        UserProfileChangeModel change =  new UserProfileChangeModel(id,action,address,phoneNumber);
        source.output().send(MessageBuilder.withPayload(change).build());
    }
    
 }
