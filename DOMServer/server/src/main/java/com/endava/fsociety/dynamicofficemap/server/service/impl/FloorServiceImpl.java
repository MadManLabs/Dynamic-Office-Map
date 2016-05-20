package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.repository.FloorRepository;
import com.endava.fsociety.dynamicofficemap.server.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Service
public class FloorServiceImpl implements FloorService {

    @Autowired
    private FloorRepository floorRepository;

    public Floor findById(String id) {
        return floorRepository.findOne(id);
    }

    public List<Floor> findAll() {
        return floorRepository.findAll();
    }

    public Floor save(Floor floor) {
        return floorRepository.save(floor);
    }

}
