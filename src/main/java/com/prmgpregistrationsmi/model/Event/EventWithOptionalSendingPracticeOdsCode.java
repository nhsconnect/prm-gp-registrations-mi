package com.prmgpregistrationsmi.model.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EventWithOptionalSendingPracticeOdsCode extends BaseEvent {
    private String sendingPracticeOdsCode;
}
