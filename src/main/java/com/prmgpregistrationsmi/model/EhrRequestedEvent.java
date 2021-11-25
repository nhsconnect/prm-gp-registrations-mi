package com.prmgpregistrationsmi.model;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
public class EhrRequestedEvent extends Event {

    @Override
    public Payload getPayload() {
        return null;
    }
}
