package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Bobby Marco
 * REST controller class that manages the Compensation endpoints for POST & GET requests
 */
@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(Compensation.class);

    @Autowired
    private CompensationService compensationService;

    // receives POST request to create compensation object with the data in the request
    @PostMapping("/compensation/{id}")
    public Compensation createComp(@PathVariable String id, @RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);
        // assign id from the POST url
        compensation.setEmployeeId(id);

        return compensationService.createComp(compensation);
    }

    // receives the id via GET request and returns compensation tied to it
    @GetMapping("/compensation/{id}")
    public Compensation readComp(@PathVariable String id) {
        LOG.debug("Received compensation read request for id [{}]", id);

        Compensation compensation = compensationService.readComp(id);
        //signifies the user if there's missing information
        if(compensation.getSalary() == null || compensation.getEffectiveDate() == null){
            LOG.debug("Missing compensation information for id [{}]", id);
        }

        return compensation;
    }
}
