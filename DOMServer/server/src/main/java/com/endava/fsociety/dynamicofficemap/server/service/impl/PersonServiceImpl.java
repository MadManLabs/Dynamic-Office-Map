package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.*;
import com.endava.fsociety.dynamicofficemap.server.repository.AssetRepository;
import com.endava.fsociety.dynamicofficemap.server.repository.PersonRepository;
import com.endava.fsociety.dynamicofficemap.server.repository.ZoneRepository;
import com.endava.fsociety.dynamicofficemap.server.service.AssetService;
import com.endava.fsociety.dynamicofficemap.server.service.BeaconService;
import com.endava.fsociety.dynamicofficemap.server.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by caldea on 5/21/2016.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AssetService assetService;

    @Autowired
    private BeaconService beaconService;

    @Override
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    @Override
    public Person findById(String id) {
        return personRepository.findOne(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person updatePermanentDesk(String personId, String assetCode) throws BadUrlException {
        Asset desk = assetRepository.findByCode(assetCode);
        Person person = personRepository.findOne(personId);
        if (desk != null && person != null) {
            person.setPermanentDesk(desk);
            person = personRepository.save(person);
        } else {
            throw new BadUrlException("The Desk or the Person were not found");
        }
        return person;
    }

    @Override
    public Person updateTemporaryZone(String personId, String temporaryZoneCode) throws BadUrlException {
        Zone zone = zoneRepository.findByCode(temporaryZoneCode);
        Person person = personRepository.findOne(personId);
        if (zone != null && person != null) {
            person.setTemporaryZone(zone);
            person = personRepository.save(person);
        } else {
            throw new BadUrlException("The Zone or the Person were not found");
        }
        return person;
    }

    @Override
    public Person updateTemporaryZoneByBeacon(String personId, String beaconUuid) throws BadUrlException {
        Beacon beacon = beaconService.findByUuid(beaconUuid);
        Person person = personRepository.findOne(personId);
        if (beacon != null && person != null) {
            if ("zone".equalsIgnoreCase(beacon.getType())) {
                Zone zone = zoneRepository.findByCode(beacon.getCode());
                if (zone != null) {
                    person.setTemporaryZone(zone);
                    person = personRepository.save(person);
                }
            } else if ("asset".equalsIgnoreCase(beacon.getType())) {
                Asset asset = assetRepository.findByCode(beacon.getCode());
                if (asset != null && asset.getZone() != null) {
                    person.setTemporaryZone(asset.getZone());
                    person = personRepository.save(person);
                }
            }
        } else {
            throw new BadUrlException("The Beacon or the Person were not found");
        }
        return person;
    }

    @Override
    public List<Person> findAllPersonsByTempZoneCode(String zoneCode) throws BadUrlException {
        Zone zone = zoneRepository.findByCode(zoneCode);
        List<Person> persons = new ArrayList<Person>();
        if (zone != null) {
            persons = personRepository.findByTemporaryZone(zone);
        } else {
            throw new BadUrlException("The zone was not found");
        }
        return persons;
    }

    @Override
    public Set<Person> findAllPersonsOnMap(Floor floor) {
        List<Asset> assets = assetService.findByMap(floor);
        Set<Person> persons = new HashSet<Person>();
        for (Asset asset : assets) {
            Person person = personRepository.findByPermanentDesk(asset);
            if (person != null) {
                persons.add(person);
            }
        }
        return persons;
    }

    @Override
    public Person findByPermanentDesk(Asset asset) {
        return personRepository.findByPermanentDesk(asset);
    }


}
