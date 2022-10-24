package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Organisation.*;

import java.util.Collections;

import static com.prmgpregistrationsmi.service.EnrichmentService.ICB_ROLE_ID;

public class OrganisationBuilder {
    public static Organisation.OrganisationBuilder withDefaultValues() {
        return Organisation.builder()
                .Organisation(OrganisationDetails.builder()
                    .Name("GP Practice 1").Rels(OrganisationRels.builder()
                    .Rel(Collections.singletonList(OrganisationRelBuilder.withStatisAndRoleId("Active", "J11", ICB_ROLE_ID).build())).build()).build());
    }

    public static Organisation.OrganisationBuilder withOnlyPracticeName(String practiceName) {
        return Organisation.builder().Organisation(OrganisationDetails.builder().Name(practiceName).build());
    }

    public static Organisation.OrganisationBuilder withPracticeIcbDetails(String practiceName, String practiceIcbOdsCode) {
        return Organisation.builder()
                .Organisation(OrganisationDetails.builder()
                        .Name(practiceName).Rels(OrganisationRels.builder()
                                .Rel(Collections.singletonList(OrganisationRelBuilder.withStatisAndRoleId("Active", practiceIcbOdsCode, ICB_ROLE_ID).build())).build()).build());
    }
}
