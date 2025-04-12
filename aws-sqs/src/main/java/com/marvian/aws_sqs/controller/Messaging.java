package com.marvian.aws_sqs.controller;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/messaging/v1")
public class Messaging {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.endpoint.uri}")
    private String endpoint;

    public Messaging(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }
    @GetMapping("/send/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
        return ResponseEntity.ok(message);
    }

}
