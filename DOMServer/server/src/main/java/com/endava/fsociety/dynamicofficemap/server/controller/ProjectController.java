package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.PersonDTO;
import com.endava.fsociety.dynamicofficemap.server.dto.ProjectDTO;
import com.endava.fsociety.dynamicofficemap.server.viewservice.PersonViewService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.ProjectViewService;
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
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectViewService projectViewService;

    @RequestMapping(value = "/beacon/{beaconUuid}", method = RequestMethod.GET)
    public List<ProjectDTO> updateTemporaryZoneByBeacon(@PathVariable("beaconUuid") String beaconUuid) {
        return projectViewService.findAllProjectsByBeaconUuid(beaconUuid);
    }

}
