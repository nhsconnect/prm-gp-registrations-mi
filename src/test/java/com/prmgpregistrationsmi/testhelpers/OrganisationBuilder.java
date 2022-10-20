package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Organisation.*;

import java.util.Collections;

public class OrganisationBuilder {
    public static Organisation.OrganisationBuilder withDefaultValues() {
        return Organisation.builder()
                .Organisation(OrganisationDetails.builder()
                    .Name("GP Practice 1").Rels(OrganisationRels.builder()
                    .Rel(Collections.singletonList(OrganisationRel.builder()
                        .Status("Active")
                        .Target(OrganisationRelTarget.builder()
                            .OrgId(OrganisationRelTargetOrgId.builder()
                                .extension("J11").build())
                            .PrimaryRoleId(OrganisationRelTargetPrimaryRoleId.builder()
                                .id("RO98").build()).build()).build())).build()).build());
    }

    public static Organisation.OrganisationBuilder withPracticeName(String practiceName) {
        return Organisation.builder().Organisation(OrganisationDetails.builder().Name(practiceName).build());
    }

    public static Organisation.OrganisationBuilder withPracticeIcbDetails(String practiceName, String practiceIcbOdsCode) {
        return Organisation.builder()
                .Organisation(OrganisationDetails.builder()
                        .Name(practiceName).Rels(OrganisationRels.builder()
                                .Rel(Collections.singletonList(OrganisationRel.builder()
                                        .Status("Active")
                                        .Target(OrganisationRelTarget.builder()
                                                .OrgId(OrganisationRelTargetOrgId.builder()
                                                        .extension(practiceIcbOdsCode).build())
                                                .PrimaryRoleId(OrganisationRelTargetPrimaryRoleId.builder()
                                                        .id("RO98").build()).build()).build())).build()).build());
    }
}
