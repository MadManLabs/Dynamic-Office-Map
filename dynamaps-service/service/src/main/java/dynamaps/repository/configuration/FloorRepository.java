
package dynamaps.repository.configuration;

import dynamaps.model.configuration.Desk;
import dynamaps.model.configuration.Floor;
import dynamaps.model.configuration.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {

}
