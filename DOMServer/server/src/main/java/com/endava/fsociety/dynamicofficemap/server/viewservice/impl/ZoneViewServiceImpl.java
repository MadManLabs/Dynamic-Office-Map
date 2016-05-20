package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.ZoneDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadDataException;
import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import com.endava.fsociety.dynamicofficemap.server.service.FloorService;
import com.endava.fsociety.dynamicofficemap.server.service.ZoneService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.ZoneViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Service
public class ZoneViewServiceImpl implements ZoneViewService {

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private FloorService floorService;

    public List<ZoneDTO> findByFloor(String floorId) {
        Floor floor = floorService.findById(floorId);
        if (floor == null) {
            throw new BadUrlException("There is no floor with id " + floorId);
        }
        List<Zone> zones = zoneService.findByFloor(floor);
        List<ZoneDTO> zoneDTOs = new ArrayList<ZoneDTO>();
        for (Zone zone : zones) {
            zoneDTOs.add(new ZoneDTO(zone));
        }
        return zoneDTOs;
    }

    public ZoneDTO save(ZoneDTO zoneDTO) {
        Zone zone;
        if (zoneDTO.getId() != null) {
            zone = zoneService.findById(zoneDTO.getId());
            if (zone == null) {
                throw new BadDataException("There is no zone with id " + zoneDTO.getId());
            }
        } else {
            zone = new Zone();
        }

        if (zoneDTO.getParentId() != null) {
            Zone parentZone = zoneService.findById(zoneDTO.getParentId());
            zone.setParent(parentZone);
        }

        Floor floor = floorService.findById(zoneDTO.getFloorId());
        if (floor == null) {
            throw new BadDataException("There is no floor with id " + zoneDTO.getFloorId());
        }
        zone.setFloor(floor);

        zone.setName(zoneDTO.getName());
        zone.setCode(zoneDTO.getCode());
        return new ZoneDTO(zoneService.save(zone));
    }

}
