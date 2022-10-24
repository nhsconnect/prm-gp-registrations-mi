package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Organisation.OrganisationRel;
import com.prmgpregistrationsmi.model.Organisation.OrganisationRelTarget;
import com.prmgpregistrationsmi.model.Organisation.OrganisationRelTargetOrgId;
import com.prmgpregistrationsmi.model.Organisation.OrganisationRelTargetPrimaryRoleId;

public class OrganisationRelBuilder {
    public static OrganisationRel.OrganisationRelBuilder withStatisAndRoleId(String status, String icbOdsCode, String roleId) {
        return OrganisationRel.builder()
                .Status(status)
                .Target(OrganisationRelTarget.builder()
                        .OrgId(OrganisationRelTargetOrgId.builder()
                                .extension(icbOdsCode).build())
                        .PrimaryRoleId(OrganisationRelTargetPrimaryRoleId.builder()
                                .id(roleId).build()).build());
    }
}
