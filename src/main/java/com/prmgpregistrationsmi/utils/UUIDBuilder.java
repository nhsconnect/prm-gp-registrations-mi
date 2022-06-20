package com.prmgpregistrationsmi.utils;

import java.util.UUID;

public class UUIDBuilder {
    public static String stringFromString(String input) {
        return UUID.nameUUIDFromBytes(input.getBytes()).toString();
    }
}
