package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Placeholder {
    @NotNull(message = "must be either SENDER or PRE_EXISTING")
    private GeneratedBy generatedBy;
    @NotNull(message = "Must be one of the following: SCANNED_DOCUMENT, " +
            "ORIGINAL_TEXT_DOCUMENT, " +
            "OCR_TEXT_DOCUMENT, " +
            "IMAGE, " +
            "AUDIO_DICTATION, " +
            "OTHER_AUDIO, " +
            "OTHER_DIGITAL_SIGNAL, " +
            "EDI_MESSAGE, " +
            "OTHER, " +
            "NOT_AVAILABLE")
    private ClinicalType clinicalType;
    @NotNull(message = "Must be one of the following: FILE_TYPE_UNSUPPORTED, " +
            "FILE_DELETED, " +
            "FILE_NOT_FOUND, " +
            "FILE_LOCKED, " +
            "UNABLE_TO_DETERMINE_PROBLEM")
    private Reason reason;
    @NotEmpty
    private String originalMimeType;
}