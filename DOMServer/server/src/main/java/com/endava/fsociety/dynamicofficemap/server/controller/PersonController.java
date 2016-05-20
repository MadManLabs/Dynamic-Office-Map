package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.PersonDTO;
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

    /**
     * TODO
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    public List<PersonDTO> findAll() {
        return null;
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public PersonDTO findByEmail(@PathVariable("email") String email) {
        return null;
    }

    @RequestMapping(value = "/{id}/permanentZone/{permanentZone}", method = RequestMethod.PUT)
    public PersonDTO updatePermanentZone(@PathVariable("id") String personId, @PathVariable("permanentZone") String permanentZoneCode) {
        return null;
    }

    @RequestMapping(value = "/{id}/temporaryZone/{temporaryZone}", method = RequestMethod.PUT)
    public PersonDTO updateTemporaryZone(@PathVariable("id") String personId, @PathVariable("temporaryZone") String temporaryZoneCode) {
        return null;
    }

}
