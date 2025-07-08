package com.prmgpregistrationsmi.model.Event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class PayloadEvent<T extends Payload> extends BaseEvent {
    T payload;

    public T getPayload(){
        return payload;
    }
}
