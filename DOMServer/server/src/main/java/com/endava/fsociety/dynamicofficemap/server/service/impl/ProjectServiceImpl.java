package com.endava.fsociety.dynamicofficemap.server.service.impl;

import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.Beacon;
import com.endava.fsociety.dynamicofficemap.server.model.BusinessProject;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import com.endava.fsociety.dynamicofficemap.server.repository.AssetRepository;
import com.endava.fsociety.dynamicofficemap.server.repository.BeaconRepository;
import com.endava.fsociety.dynamicofficemap.server.repository.ProjectRepository;
import com.endava.fsociety.dynamicofficemap.server.repository.ZoneRepository;
import com.endava.fsociety.dynamicofficemap.server.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caldea on 5/21/2016.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private BeaconRepository beaconRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public List<BusinessProject> findAllProjectsByBeaconUuid(String beaconUuid) {
        Beacon beacon = beaconRepository.findByUuid(beaconUuid);
        List<BusinessProject> businessProjects = new ArrayList<BusinessProject>();
        if (beacon != null) {
            if ("zone".equalsIgnoreCase(beacon.getType())) {
                Zone zone = zoneRepository.findByCode(beacon.getCode());
                if (zone != null) {
                    findProjectsFromZone(zone, businessProjects);
                }
            } else if ("asset".equalsIgnoreCase(beacon.getType())) {
                Asset asset = assetRepository.findByCode(beacon.getCode());
                if (asset != null && asset.getZone() != null) {
                    findProjectsFromZone(asset.getZone(), businessProjects);
                }
            }
        }
        return businessProjects;
    }

    private void findProjectsFromZone(Zone parentZone, List<BusinessProject> businessProjects) {
        List<BusinessProject> projectsFromZone = projectRepository.findByZone(parentZone);
        businessProjects.addAll(projectsFromZone);
        List<Zone> zones = zoneRepository.findByParent(parentZone);
        for (Zone zone : zones) {
            findProjectsFromZone(zone, businessProjects);
        }
    }

}
