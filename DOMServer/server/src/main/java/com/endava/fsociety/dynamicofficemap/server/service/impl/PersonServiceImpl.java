package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.Person;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import com.endava.fsociety.dynamicofficemap.server.repository.AssetRepository;
import com.endava.fsociety.dynamicofficemap.server.repository.PersonRepository;
import com.endava.fsociety.dynamicofficemap.server.repository.ZoneRepository;
import com.endava.fsociety.dynamicofficemap.server.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
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


}
