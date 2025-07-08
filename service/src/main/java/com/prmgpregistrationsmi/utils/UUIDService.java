package com.prmgpregistrationsmi.utils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class UUIDService {
    public static String buildUUIDStringFromSeed(String seed) {
        return UUID.nameUUIDFromBytes(seed.getBytes(StandardCharsets.UTF_8)).toString();
    }
}
