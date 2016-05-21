package com.endava.fsociety.dynamicofficemap.server.service;

import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;
import com.endava.fsociety.dynamicofficemap.server.model.Person;

import java.util.List;

/**
 * Created by caldea on 5/21/2016.
 */
public interface PersonService {
    Person findByEmail(String email);

    List<Person> findAll();

    Person updatePermanentDesk(String personId, String assetCode) throws BadUrlException;

    Person updateTemporaryZone(String personId, String temporaryZoneCode) throws BadUrlException;

    List<Person> findAllPersonsByTempZoneCode(String zoneCode) throws BadUrlException;
}
