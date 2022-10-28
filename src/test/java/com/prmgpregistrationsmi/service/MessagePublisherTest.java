package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.utils.JsonHelper;
import org.json.simple.JSONObject;
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

    private JSONObject generateTestObject() {
        JSONObject message = new JSONObject();
        message.put("bool", true);
        message.put("else",2);
        message.put("something", "value");
        return message;
    }

    @Test
    void shouldPublishMessageToSns() {
        JSONObject message = generateTestObject();

        String eventId = "some-id";

        PublishRequest expectedRequest = PublishRequest.builder()
                .message(JsonHelper.asJsonString(message))
                .topicArn(topicArn)
                .build();

        String messageId = "someMessageId";
        PublishResponse publishResponse = PublishResponse.builder().messageId(messageId).build();

        when(snsClient.publish(expectedRequest)).thenReturn(publishResponse);

        messagePublisher.sendMessage(message, eventId);
        verify(snsClient).publish(expectedRequest);
    }
}