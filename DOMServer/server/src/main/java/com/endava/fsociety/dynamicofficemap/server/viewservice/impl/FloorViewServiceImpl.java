package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.FloorDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadDataException;
import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.service.FloorService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.FloorViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Service
public class FloorViewServiceImpl implements FloorViewService {

    @Autowired
    private FloorService floorService;

    public FloorDTO findById(String id) {
        Floor floor = floorService.findById(id);
        if (floor == null) {
            throw new BadUrlException("There is no floor with id " + id);
        }
        return new FloorDTO(floor);
    }

    public List<FloorDTO> findAll() {
        List<Floor> floors = floorService.findAll();
        List<FloorDTO> floorDTOs = new ArrayList<FloorDTO>();
        for (Floor floor : floors) {
            floorDTOs.add(new FloorDTO(floor));
        }
        return floorDTOs;
    }

    public FloorDTO save(FloorDTO floorDTO) {
        Floor floor;
        if (floorDTO.getId() != null) {
            floor = floorService.findById(floorDTO.getId());
            if (floor == null) {
                throw new BadDataException("There is no floor with id " + floorDTO.getId());
            }
        } else {
            floor = new Floor();
        }

        floor.setName(floorDTO.getName());
        floor.setMap(floorDTO.getMap());
        return new FloorDTO(floorService.save(floor));
    }
}
