package com.prmgpregistrationsmi.model.Organisation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class OrganisationRelDetails {
    private String Status;
    private OrganisationRelTarget Target;
}
