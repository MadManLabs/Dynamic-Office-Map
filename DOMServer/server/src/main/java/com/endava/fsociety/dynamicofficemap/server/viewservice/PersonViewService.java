package com.endava.fsociety.dynamicofficemap.server.viewservice;

import com.endava.fsociety.dynamicofficemap.server.dto.PersonDTO;
import com.endava.fsociety.dynamicofficemap.server.exception.BadUrlException;

import java.util.List;

/**
 * Created by caldea on 5/21/2016.
 */
public interface PersonViewService {

    List<PersonDTO> findAllPersons();

    PersonDTO findByUsername(String username);

    PersonDTO updatePermanentDesk(String personId, String assetCode) throws BadUrlException;

    PersonDTO updateTemporaryZone(String personId, String temporaryZoneCode) throws BadUrlException;

    PersonDTO updateTemporaryZoneByBeaconId(String personId, String beaconUuid) throws BadUrlException;

    List<PersonDTO> findAllPersonsByTempZoneCode(String zoneCode) throws BadUrlException;

    List<PersonDTO> findAllPersonsOnMap(String floorId);

    /**
     * This will find the person that has this permanent desk.
     *
     * @param assetId
     * @return
     */

    PersonDTO findPersonByPermanentDesk(String assetId);

    /**
     * This will find all the persons that have a permanent desk in this zone or in all the child zones.
     *
     * @param zoneId
     * @return
     */

    List<PersonDTO> findPersonByPermanentZone(String zoneId);

    PersonDTO findTenantByAssetId(String assetId);

    PersonDTO findById(String personId);

}
