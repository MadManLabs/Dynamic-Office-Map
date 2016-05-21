package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.PersonDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.Person;
import com.endava.fsociety.dynamicofficemap.server.service.PersonService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.PersonViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caldea on 5/21/2016.
 */
@Service
public class PersonViewServiceImpl implements PersonViewService {

    @Autowired
    private PersonService personService;

    @Override
    public List<PersonDTO> findAllPersons() {
        List<Person> persons = personService.findAll();
        List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
        for (Person person : persons) {
            personDTOs.add(new PersonDTO(person));
        }
        return personDTOs;
    }


    @Override
    public PersonDTO findByUsername(String username) {
        Person person = personService.findByUsername(username);
        if (person == null) {
            throw new BadUrlException("There is no person with username " + username);
        }
        return new PersonDTO(person);
    }

    @Override
    public PersonDTO updatePermanentDesk(String personId, String assetCode) throws BadUrlException {
        return new PersonDTO(personService.updatePermanentDesk(personId, assetCode));
    }

    @Override
    public PersonDTO updateTemporaryZone(String personId, String temporaryZoneCode) throws BadUrlException {
        return new PersonDTO(personService.updateTemporaryZone(personId, temporaryZoneCode));
    }

    @Override
    public List<PersonDTO> findAllPersonsByTempZoneCode(String zoneCode) throws BadUrlException {
        List<Person> persons = personService.findAllPersonsByTempZoneCode(zoneCode);
        List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
        for (Person person : persons) {
            personDTOs.add(new PersonDTO(person));
        }
        return personDTOs;
    }
}
