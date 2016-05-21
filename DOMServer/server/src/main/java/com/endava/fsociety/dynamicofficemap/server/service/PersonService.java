package com.endava.fsociety.dynamicofficemap.server.service;

import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.model.Person;

import java.util.List;
import java.util.Set;

/**
 * Created by caldea on 5/21/2016.
 */

public interface PersonService {

    Person findByUsername(String email);

    Person findById(String id);

    List<Person> findAll();

    Person updatePermanentDesk(String personId, String assetCode) throws BadUrlException;

    Person updateTemporaryZone(String personId, String temporaryZoneCode) throws BadUrlException;

    List<Person> findAllPersonsByTempZoneCode(String zoneCode) throws BadUrlException;

    Set<Person> findAllPersonsOnMap(Floor floor);

    Person findByPermanentDesk(Asset asset);

}
