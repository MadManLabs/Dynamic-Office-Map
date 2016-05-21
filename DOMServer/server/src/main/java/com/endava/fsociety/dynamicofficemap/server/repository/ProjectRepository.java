package com.endava.fsociety.dynamicofficemap.server.repository;

import com.endava.fsociety.dynamicofficemap.server.model.BusinessProject;
import com.endava.fsociety.dynamicofficemap.server.model.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@Repository
public interface ProjectRepository extends MongoRepository<BusinessProject, String> {

    List<BusinessProject> findByZone(Zone zone);

}
