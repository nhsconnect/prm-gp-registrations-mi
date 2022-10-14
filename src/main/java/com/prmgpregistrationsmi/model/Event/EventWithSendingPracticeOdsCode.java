package com.prmgpregistrationsmi.model.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EventWithSendingPracticeOdsCode extends BaseEvent {
    @NotEmpty
    private String sendingPracticeOdsCode;
}
