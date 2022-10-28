package com.prmgpregistrationsmi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@Component
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class MessagePublisher {
    private final SnsClient snsClient;
    private final String snsTopicArn;

    public MessagePublisher(SnsClient snsClient,  @Value("${mi_events_sns_topic_arn}") String snsTopicArn) {
        this.snsClient = snsClient;
        this.snsTopicArn = snsTopicArn;
    }

    public void sendMessage(String topicArn, String message, String eventId) {
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .topicArn(snsTopicArn)
                .build();

        logger.info("Sending message to {}", snsTopicArn);
        PublishResponse result = snsClient.publish(request);
        logger.info("PUBLISHED: message to {} topic. Published SNS message id: {} with event id: {}", topicArn,
                result.messageId(), eventId);
    }
}

