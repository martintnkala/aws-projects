package com.marvian.aws_sqs.controller;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@RestController
//@RequestMapping("/messaging/v1")
public class Messaging {

    Logger logger = LoggerFactory.getLogger(Messaging.class);

    private final SqsAsyncClient sqsAsyncClient;

    @Value("${cloud.aws.endpoint.uri}")
    private String endpoint;

    public Messaging( SqsAsyncClient sqsAsyncClient) {
        this.sqsAsyncClient = sqsAsyncClient;
    }

    @GetMapping("/sendMessage/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        SendMessageRequest sendMessageRequest = SendMessageRequest
                .builder()
                .queueUrl(endpoint)
                .messageBody(message)
                .build();
        sqsAsyncClient.sendMessage(sendMessageRequest);
        return ResponseEntity.ok("Sent: " + message);
    }

    @SqsListener("SpringBoot")
    public void loadMessageFromQueue(String message) {
        logger.info("Message from SQS Queue: {}", message);
//        queueMessagingTemplate.convertAndSend(endpoint, message);
    }

}
