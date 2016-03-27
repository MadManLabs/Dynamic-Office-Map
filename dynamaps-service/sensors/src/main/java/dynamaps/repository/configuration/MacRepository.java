package dynamaps.repository.configuration;

import dynamaps.model.configuration.Mac;
import dynamaps.model.configuration.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by caldea on 3/27/2016.
 */
@Repository
public interface MacRepository extends JpaRepository<Mac, Integer> {
    Mac findByValue(String value);
}
