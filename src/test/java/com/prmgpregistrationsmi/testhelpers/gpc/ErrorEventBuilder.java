package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.Error.ErrorEvent;
import com.prmgpregistrationsmi.model.gpc.Error.ErrorPayload;
import com.prmgpregistrationsmi.testhelpers.ErrorDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;

public class ErrorEventBuilder {
    public static ErrorEvent.ErrorEventBuilder<?, ?> withDefaultEventValues() {
        return ErrorEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultErrorPayload().build());
    }

    public static ErrorPayload.ErrorPayloadBuilder withDefaultErrorPayload() {
        return ErrorPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .error(ErrorDetailsBuilder.withDefaultValues().build());
    }
}
