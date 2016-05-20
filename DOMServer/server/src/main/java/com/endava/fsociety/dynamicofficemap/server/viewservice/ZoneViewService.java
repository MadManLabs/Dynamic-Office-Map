package com.endava.fsociety.dynamicofficemap.server.viewservice;

import com.endava.fsociety.dynamicofficemap.server.dto.ZoneDTO;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface ZoneViewService {

    List<ZoneDTO> findByFloor(String floorId);

    ZoneDTO save(ZoneDTO zoneDTO);

}
