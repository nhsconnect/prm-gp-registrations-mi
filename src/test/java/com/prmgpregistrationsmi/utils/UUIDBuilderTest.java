package com.prmgpregistrationsmi.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UUIDBuilderTest {
    @Test
    void shouldReturnUUIDStringFromAnotherString() {
        String input = "a random string";
        String result1 = UUIDService.buildUUIDStringFromSeed(input);

        assertEquals(result1.length(), 36);
    }

    @Test
    void shouldReturnSameUUIDGivenTheSameString() {
        String input = "a random string";
        String result1 = UUIDService.buildUUIDStringFromSeed(input);
        String result2 = UUIDService.buildUUIDStringFromSeed(input);

        assertEquals(result1, result2);
    }
}