package com.endava.fsociety.dynamicofficemap.server.service;

import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface ZoneService {

    Zone findById(String id);

    List<Zone> findByFloor(Floor floor);

    List<Zone> findByParent(Zone parent);

    Zone save(Zone zone);

}
