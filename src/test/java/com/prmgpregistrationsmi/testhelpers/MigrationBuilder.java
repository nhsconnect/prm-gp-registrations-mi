package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Migration;
import com.prmgpregistrationsmi.model.Event.EventPayload.Status;

public class MigrationBuilder {
    public static Migration.MigrationBuilder withSuccessfulMigration() {
        return Migration.builder()
                .status(Status.SUCCESS)
                .reason("ok");
    }
}
