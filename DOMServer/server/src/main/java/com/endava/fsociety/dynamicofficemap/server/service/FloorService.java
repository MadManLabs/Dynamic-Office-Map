package com.endava.fsociety.dynamicofficemap.server.service;

import com.endava.fsociety.dynamicofficemap.server.model.Floor;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

public interface FloorService {

    Floor findById(String id);

    List<Floor> findAll();

    Floor save(Floor floor);

}
