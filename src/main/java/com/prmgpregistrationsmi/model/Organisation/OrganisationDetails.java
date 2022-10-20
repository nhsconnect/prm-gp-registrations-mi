package com.prmgpregistrationsmi.model.Organisation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class OrganisationDetails {
    private String Name;
    private OrganisationRels Rels;
}
