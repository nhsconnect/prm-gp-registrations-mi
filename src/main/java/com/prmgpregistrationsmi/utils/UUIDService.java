package com.prmgpregistrationsmi.utils;

import java.util.UUID;

public class UUIDService {
    public static String buildUUIDStringFromSeed(String seed) {
        return UUID.nameUUIDFromBytes(seed.getBytes()).toString();
    }
}
