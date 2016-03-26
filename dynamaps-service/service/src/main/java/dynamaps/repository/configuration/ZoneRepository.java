
package dynamaps.repository.configuration;

import dynamaps.model.configuration.Person;
import dynamaps.model.configuration.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {

	List<Zone> findByFloorId(Integer id);
}
