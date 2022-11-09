package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.utils.JsonHelper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@Component
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public record MessageSender(SqsClient sqsClient,
                            String sqsQueueUrl) {
    public MessageSender(SqsClient sqsClient,
                         @Value("${mi_events_sqs_queue_for_event_enrichment_url}") String sqsQueueUrl) {
        this.sqsClient = sqsClient;
        this.sqsQueueUrl = sqsQueueUrl;
    }

    public void send(Object object, String messageId) {
        String messageBody = JsonHelper.asJsonString(object);

        SendMessageRequest messageRequest = SendMessageRequest.builder()
                .queueUrl(sqsQueueUrl)
                .messageBody(messageBody)
                .build();

        logger.info("Attempting to send message to: " + sqsQueueUrl, messageBody);

        SendMessageResponse response = sqsClient.sendMessage(messageRequest);

        logger.info("Successfully sent message to: " + sqsQueueUrl + ". Message id: " + response.messageId() + ", " +
                "with event id: " + messageId, messageBody);
    }
}

