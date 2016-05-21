package com.endava.fsociety.dynamicofficemap.server.viewservice;

import com.endava.fsociety.dynamicofficemap.server.dto.PersonDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;

import java.util.List;

/**
 * Created by caldea on 5/21/2016.
 */
public interface PersonViewService {

    List<PersonDTO> findAllPersons();

    PersonDTO findByEmail(String email);

    PersonDTO updatePermanentDesk(String personId, String assetCode) throws BadUrlException;

    PersonDTO updateTemporaryZone(String personId, String temporaryZoneCode) throws BadUrlException;

    List<PersonDTO> findAllPersonsByTempZoneCode(String zoneCode) throws BadUrlException;
}
