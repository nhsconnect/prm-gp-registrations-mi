package com.prmgpregistrationsmi.model.Event;

import com.prmgpregistrationsmi.utils.JsonHelper;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SplunkEventDAO {
    private String source;
    private Object event;

    public static SplunkEventDAO fromEventDAO(Object DAO) {
        return new SplunkEventDAO("itoc:gp2gp", DAO);
    }

    @Override
    public String toString() {
        return  JsonHelper.asJsonString(this);
    }
}
