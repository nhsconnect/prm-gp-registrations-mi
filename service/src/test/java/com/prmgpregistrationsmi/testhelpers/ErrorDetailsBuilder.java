package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.ErrorDetails;
import com.prmgpregistrationsmi.model.Event.EventPayload.FailurePoint;

public class ErrorDetailsBuilder {
    public static ErrorDetails.ErrorDetailsBuilder withDefaultValues() {
        return ErrorDetails.builder()
                .errorCode("404")
                .errorDescription("stacktrace? detailed error message?")
                .failurePoint(FailurePoint.OTHER)
                .otherFailurePoint("other failure");
    }
}
