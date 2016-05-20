package com.endava.fsociety.dynamicofficemap.server.repository;

import com.endava.fsociety.dynamicofficemap.server.model.Floor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fstancu on 5/11/2016.
 */

@Repository
public interface FloorRepository extends MongoRepository<Floor, String> {
}
