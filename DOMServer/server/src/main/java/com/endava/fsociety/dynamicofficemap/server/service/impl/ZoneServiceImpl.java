package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import com.endava.fsociety.dynamicofficemap.server.repository.ZoneRepository;
import com.endava.fsociety.dynamicofficemap.server.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public Zone findById(String id) {
        return zoneRepository.findOne(id);
    }

    public List<Zone> findByFloor(Floor floor) {
        return zoneRepository.findByFloor(floor);
    }

    public List<Zone> findByParent(Zone parent) {
        return zoneRepository.findByParent(parent);
    }

    public Zone save(Zone zone) {
        return zoneRepository.save(zone);
    }

}
