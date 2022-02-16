package com.prmgpregistrationsmi.model.Event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class Event implements HasPayload {
    @NotEmpty
    private String eventId;
    @NotNull
    private LocalDateTime eventGeneratedDateTime;
    @Length(min = 4, max = 32)
    @Pattern(regexp="[A-Za-z0-9-_]+", message="must only contain letters, numbers, dashes or underscores")
    @NotEmpty
    private String registrationId;
    @NotEmpty
    private String reportingSystemSupplier;
    @NotEmpty
    private String reportingPracticeOdsCode;
}
