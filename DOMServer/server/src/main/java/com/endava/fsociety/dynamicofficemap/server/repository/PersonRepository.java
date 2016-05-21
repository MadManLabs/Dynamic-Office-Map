package com.endava.fsociety.dynamicofficemap.server.repository;

import com.endava.fsociety.dynamicofficemap.server.model.Asset;
import com.endava.fsociety.dynamicofficemap.server.model.Person;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    Person findByUsername(String username);

    Person findByPermanentDesk(Asset asset);

    List<Person> findByTemporaryZone(Zone zone);

}
