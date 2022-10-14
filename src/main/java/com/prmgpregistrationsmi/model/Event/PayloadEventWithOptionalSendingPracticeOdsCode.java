package com.prmgpregistrationsmi.model.Event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class PayloadEventWithOptionalSendingPracticeOdsCode<T extends Payload> extends EventWithOptionalSendingPracticeOdsCode {
    T payload;

    public T getPayload(){
        return payload;
    }
}