package com.endava.fsociety.dynamicofficemap.server.viewservice.impl;

import com.endava.fsociety.dynamicofficemap.server.dto.ProjectDTO;
import com.endava.fsociety.dynamicofficemap.server.model.BusinessProject;
import com.endava.fsociety.dynamicofficemap.server.service.ProjectService;
import com.endava.fsociety.dynamicofficemap.server.viewservice.ProjectViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caldea on 5/21/2016.
 */
@Service
public class ProjectViewServiceImpl implements ProjectViewService {

    @Autowired
    private ProjectService projectService;

    @Override
    public List<ProjectDTO> findAllProjectsByBeaconUuid(String beaconUuid) {
        List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();
        List<BusinessProject> businessProjects = projectService.findAllProjectsByBeaconUuid(beaconUuid);
        for (BusinessProject businessProject : businessProjects) {
            projectDTOs.add(new ProjectDTO(businessProject));
        }
        return projectDTOs;
    }
}
