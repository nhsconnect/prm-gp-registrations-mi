package com.prmgpregistrationsmi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonHelper {
    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
