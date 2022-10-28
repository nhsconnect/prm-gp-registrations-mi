package com.prmgpregistrationsmi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessagePublisherTest {
    @Mock
    private SnsClient snsClient;
    private final static String topicArn = "topicArn";
    private MessagePublisher messagePublisher;

    @BeforeEach
    void setUp() {
        messagePublisher = new MessagePublisher(snsClient, topicArn);
    }

    @Test
    void shouldPublishMessageToSns() {
        String message = "someMessage";
        String eventId = "some-id";

        PublishRequest expectedRequest = PublishRequest.builder()
                .message(message)
                .topicArn(topicArn)
                .build();

        String messageId = "someMessageId";
        PublishResponse publishResponse = PublishResponse.builder().messageId(messageId).build();

        when(snsClient.publish(expectedRequest)).thenReturn(publishResponse);

        messagePublisher.sendMessage(topicArn, message, eventId);
        verify(snsClient).publish(expectedRequest);
    }
}