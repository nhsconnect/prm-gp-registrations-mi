package com.prmgpregistrationsmi.model.Organisation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class OrganisationRel {
    private String Status;
    private OrganisationRelTarget Target;
}
