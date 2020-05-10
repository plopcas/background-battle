package com.plopcas.bb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CleanUpService {
    private static Logger log = LoggerFactory.getLogger(CleanUpService.class);

    public CleanUpService() {
    }

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void cleanUp() {
        // Delete old tournaments
    }

}
