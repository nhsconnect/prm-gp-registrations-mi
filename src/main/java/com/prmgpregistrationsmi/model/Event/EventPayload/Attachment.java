package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Attachment {
    @NotNull(message = "Must be one of the following: SCANNED_DOCUMENT, " +
            "ORIGINAL_TEXT_DOCUMENT," +
            "OCR_TEXT_DOCUMENT," +
            "IMAGE," +
            "AUDIO_DICTATION," +
            "OTHER_AUDIO," +
            "OTHER_DIGITAL_SIGNAL," +
            "EDI_MESSAGE," +
            "NOT_AVAILABLE," +
            "OTHER")
    private ClinicianType clinicalType;
    @NotEmpty
    private String mimeType;
    @NotNull
    @PositiveOrZero
    private Long sizeBytes;
}
