package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.utils.JsonHelper;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageSenderTest {
    @Mock
    private SqsClient sqsClient;
    private final static String sqsUrl = "sqsUrl";
    private MessageSender messageSender;

    @BeforeEach
    void setUp() {
        messageSender = new MessageSender(sqsClient, sqsUrl);
    }

    private JSONObject generateTestObject() {
        JSONObject message = new JSONObject();
        message.put("bool", true);
        message.put("else",2);
        message.put("something", "value");
        return message;
    }

    @Test
    void shouldPublishMessageToSqs() {
        JSONObject message = generateTestObject();
        String eventId = "some-id";

        SendMessageRequest expectedRequest = SendMessageRequest.builder()
                .queueUrl(sqsUrl + "fds")
                .messageBody(JsonHelper.asJsonString(message))
                .build();

        String messageId = "someMessageId";
        SendMessageResponse mockedResponse = SendMessageResponse.builder().messageId(messageId).build();

        when(sqsClient.sendMessage(expectedRequest)).thenReturn(mockedResponse);

        messageSender.send(message, eventId);
        verify(sqsClient).sendMessage(expectedRequest);
    }
}