package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.PersonDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.model.Person;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import com.endava.fsociety.dynamicofficemap.server.service.AssetService;
import com.endava.fsociety.dynamicofficemap.server.service.FloorService;
import com.endava.fsociety.dynamicofficemap.server.service.PersonService;
import com.endava.fsociety.dynamicofficemap.server.service.ZoneService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.PersonViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by caldea on 5/21/2016.
 */

@Service
public class PersonViewServiceImpl implements PersonViewService {

    @Autowired
    private PersonService personService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private ZoneService zoneService;

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

    @Override
    public List<PersonDTO> findAllPersonsOnMap(String floorId) {
        Floor floor = floorService.findById(floorId);
        if (floor == null) {
            throw new BadUrlException("There is no floor with id " + floorId);
        }
        Set<Person> persons = personService.findAllPersonsOnMap(floor);
        List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
        for (Person person : persons) {
            personDTOs.add(new PersonDTO(person));
        }
        return personDTOs;
    }

    @Override
    public PersonDTO findPersonByPermanentDesk(String assetId) {
        Asset asset = assetService.findById(assetId);
        if (asset == null) {
            throw new BadUrlException("There is no assed with id " + assetId);
        }
        Person person = personService.findByPermanentDesk(asset);
        if (person == null) {
            return null;
        }
        return new PersonDTO(person);
    }

    @Override
    public List<PersonDTO> findPersonByPermanentZone(String zoneId) {
        Zone zone = zoneService.findById(zoneId);
        if (zone == null) {
            throw new BadUrlException("There is no zone with id " + zoneId);
        }
        List<Asset> assets = new ArrayList<Asset>();
        findAssetsFromMapZone(zone, assets);

        List<PersonDTO> persons = new ArrayList<PersonDTO>();
        for (Asset asset : assets) {
            Person person = personService.findByPermanentDesk(asset);
            if (person != null) {
                persons.add(new PersonDTO(person));
            }
        }

        return persons;
    }

    @Override
    public PersonDTO findTenantByAssetId(String assetId) {
        Asset asset = assetService.findById(assetId);
        if (asset == null) {
            throw new BadUrlException("There is no asset with id " + assetId);
        }
        if (asset.getTenant() == null) {
            return null;
        }
        return new PersonDTO(asset.getTenant());
    }

    @Override
    public PersonDTO findById(String personId) {
        Person person = personService.findById(personId);
        if (person == null) {
            throw new BadUrlException("There is no person with id " + personId);
        }
        return new PersonDTO(person);
    }

    private void findAssetsFromMapZone(Zone parentZone, List<Asset> assets) {
        List<Asset> assetsFromZone = assetService.findByZoneMap(parentZone);
        assets.addAll(assetsFromZone);
        List<Zone> zones = zoneService.findByParent(parentZone);
        for (Zone zone : zones) {
            findAssetsFromMapZone(zone, assets);
        }
    }

}
