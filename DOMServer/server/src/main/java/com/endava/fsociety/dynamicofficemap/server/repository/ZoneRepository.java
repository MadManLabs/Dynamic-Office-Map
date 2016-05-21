package com.endava.fsociety.dynamicofficemap.server.repository;

import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Repository
public interface ZoneRepository extends MongoRepository<Zone, String> {

    List<Zone> findByFloor(Floor floor);

    List<Zone> findByParent(Zone parent);

    Zone findByCode(String code);

}
