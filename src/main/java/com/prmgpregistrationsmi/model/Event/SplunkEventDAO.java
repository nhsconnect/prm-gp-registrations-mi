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
    private EventDAO event;

    public static SplunkEventDAO fromEventDAO(EventDAO eventDAO) {
        return new SplunkEventDAO("itoc:gp2gp", eventDAO);
    }

    @Override
    public String toString() {
        return  JsonHelper.asJsonString(this);
    }
}
