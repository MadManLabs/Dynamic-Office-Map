package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.PersonDTO;
import com.endava.fsociety.dynamicofficemap.server.viewservice.PersonViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fstancu on 5/20/2016.
 */

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonViewService personViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<PersonDTO> findAll() {
        return personViewService.findAllPersons();
    }

    @RequestMapping(value = "/zone/{temporaryZone}", method = RequestMethod.GET)
    public List<PersonDTO> findAllInZone(@PathVariable("temporaryZone") String temporaryZoneCode) {
        return personViewService.findAllPersonsByTempZoneCode(temporaryZoneCode);
    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public PersonDTO findByUsername(@PathVariable("username") String username) {
        return personViewService.findByUsername(username);
    }

    @RequestMapping(value = "/{id}/permanentDesk/{assetCode}", method = RequestMethod.PUT)
    public PersonDTO updatePermanentZone(@PathVariable("id") String personId, @PathVariable("assetCode") String assetCode) {
        return personViewService.updatePermanentDesk(personId, assetCode);
    }

    @RequestMapping(value = "/{id}/temporaryZone/{temporaryZone}", method = RequestMethod.PUT)
    public PersonDTO updateTemporaryZone(@PathVariable("id") String personId, @PathVariable("temporaryZone") String temporaryZoneCode) {
        return personViewService.updateTemporaryZone(personId, temporaryZoneCode);
    }

}
