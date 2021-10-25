package com.prmgpregistrationsmi.exception;

public class RegistrationIdMismatchedException extends Exception {
    public RegistrationIdMismatchedException() {
        super("registrationId in the path does not match registrationId in the request");
    }
}
