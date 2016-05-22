package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.FloorDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadDataException;
import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.model.Person;
import com.endava.fsociety.dynamicofficemap.server.service.FloorService;
import com.endava.fsociety.dynamicofficemap.server.service.PersonService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.FloorViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by fstancu on 5/11/2016.
 */

@Service
public class FloorViewServiceImpl implements FloorViewService {

    @Autowired
    private FloorService floorService;

    @Autowired
    private PersonService personService;

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
            Set<Person> persons = personService.findAllPersonsOnMap(floor);
            FloorDTO floorDTO = new FloorDTO(floor);
            floorDTO.setPersons(persons.size());
            floorDTOs.add(floorDTO);
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
