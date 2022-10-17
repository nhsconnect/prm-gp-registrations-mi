package com.prmgpregistrationsmi.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Organisation {
    private OrganisationDetails organisation;
}
