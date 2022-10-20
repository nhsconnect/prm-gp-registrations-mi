package com.prmgpregistrationsmi.model.Organisation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class OrganisationRel {
    private List<OrganisationRelDetails> Rel;
}
