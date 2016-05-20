package com.endava.fsociety.dynamicofficemap.server.repository;

import com.endava.fsociety.dynamicofficemap.server.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fstancu on 5/11/2016.
 */

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
}
