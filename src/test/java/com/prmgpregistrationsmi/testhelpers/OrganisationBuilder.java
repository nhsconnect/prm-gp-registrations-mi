package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Organisation.Organisation;
import com.prmgpregistrationsmi.model.Organisation.OrganisationName;

public class OrganisationBuilder {
    public static Organisation.OrganisationBuilder withDefaultValues() {
        return Organisation.builder().Organisation(OrganisationName.builder().Name("GP Practice 1").build());
    }

    public static Organisation.OrganisationBuilder withPracticeName(String practiceName) {
        return Organisation.builder().Organisation(OrganisationName.builder().Name(practiceName).build());
    }
}
