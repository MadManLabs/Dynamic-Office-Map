
package dynamaps.repository.configuration;

import dynamaps.model.configuration.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dynamaps.model.configuration.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person findByName(String name);

	List<Person> findByDeskZoneFloorId(Integer id);

	Person findByDesk(Desk desk);
}
