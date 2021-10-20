package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.EventDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class S3Client {
    public void uploadObject(EventDAO eventDAO) {
        log.info("Uploading to S3 with registration: " + eventDAO.getRegistrationId());
    }
}