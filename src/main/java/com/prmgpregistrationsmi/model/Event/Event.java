package com.prmgpregistrationsmi.model.Event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class Event implements HasPayload {
    @NotEmpty
    private String eventId;
    @NotNull
    private Long eventGeneratedTimestamp;
    @Length(min = 4, max = 32)
    @NotEmpty
    private String registrationId;
    @NotEmpty
    private String reportingSystemSupplier;
    @NotEmpty
    private String reportingPracticeOdsCode;
}
