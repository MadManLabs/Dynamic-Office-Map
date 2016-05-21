package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.model.Beacon;
import com.endava.fsociety.dynamicofficemap.server.repository.BeaconRepository;
import com.endava.fsociety.dynamicofficemap.server.service.BeaconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caldea on 5/21/2016.
 */
@Service
public class BeaconServiceImpl implements BeaconService {

    @Autowired
    private BeaconRepository beaconRepository;

    @Override
    public Beacon findByUuid(String uuid) {
        return beaconRepository.findByUuid(uuid);
    }
}
