package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Bobby Marco
 * Manages the logic or inserting and fetching compensation objects into the compensation repo
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;


    //insert new compensation entry from POST request in the database
    @Override
    public Compensation createComp(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        compensationRepository.insert(compensation);

        return compensation;
    }


    //retrieve compensation entry from the database as per GET request
    @Override
    public Compensation readComp(String id) {
        LOG.debug("Creating compensation with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployeeId(id);

        // throws exception is compensation instance doesn't exist for given id
        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return compensation;
    }

}