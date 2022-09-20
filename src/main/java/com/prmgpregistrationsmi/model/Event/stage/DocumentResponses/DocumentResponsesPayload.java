package com.prmgpregistrationsmi.model.Event.stage.DocumentResponses;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public class DocumentResponsesPayload implements Payload {
    @Valid
    @NotNull
    private Attachment attachment;
    @Valid
    @NotNull
    private StatusDetails documentMigration;
}
