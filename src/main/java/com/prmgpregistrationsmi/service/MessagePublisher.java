package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.utils.JsonHelper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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

    public MessagePublisher(SnsClient snsClient, @Value("${mi_events_sns_topic_arn}") String snsTopicArn) {
        this.snsClient = snsClient;
        this.snsTopicArn = snsTopicArn;
    }

    public void sendMessage(Object object, String messageId) {
        String message = JsonHelper.asJsonString(object);
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .topicArn(snsTopicArn)
                .build();

        logger.info("Attempting to send message to: " + snsTopicArn, message);
        PublishResponse result = snsClient.publish(request);
        logger.info("Successfully sent message to: " + snsTopicArn + ". Published SNS message id: " + result.messageId() +", with event id: " + messageId, message);
    }
}

