package com.endava.fsociety.dynamicofficemap.server.viewservice;

import com.endava.fsociety.dynamicofficemap.server.dto.AssetDTO;
import com.endava.fsociety.dynamicofficemap.server.dto.ProjectDTO;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface ProjectViewService {

    List<ProjectDTO> findAllProjectsByBeaconUuid(String beaconUuid);
}
