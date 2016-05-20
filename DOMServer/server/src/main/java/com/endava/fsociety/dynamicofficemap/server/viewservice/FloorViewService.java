package com.endava.fsociety.dynamicofficemap.server.viewservice;

import com.endava.fsociety.dynamicofficemap.server.dto.FloorDTO;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface FloorViewService {

    FloorDTO findById(String id);

    List<FloorDTO> findAll();

    FloorDTO save(FloorDTO floorDTO);

}
