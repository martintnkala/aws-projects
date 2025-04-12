package com.marvian.aws_sqs;

import io.awspring.cloud.context.config.annotation.ContextStackConfiguration;
import io.awspring.cloud.messaging.config.annotation.EnableSqs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class AwsSqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSqsApplication.class, args);
	}

}
