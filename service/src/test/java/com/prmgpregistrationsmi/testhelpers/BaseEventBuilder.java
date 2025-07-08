package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.*;
import org.springframework.stereotype.Component;

@Component
public class BaseEventBuilder {
    public static BaseEvent.BaseEventBuilder<?, ?> withDefaultEventValues() {
        return BaseEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .requestingPracticeOdsCode(DefaultEventValues.REQUESTING_PRACTICE_ODS_CODE)
                .sendingPracticeOdsCode(DefaultEventValues.SENDING_PRACTICE_ODS_CODE);
    }
}
