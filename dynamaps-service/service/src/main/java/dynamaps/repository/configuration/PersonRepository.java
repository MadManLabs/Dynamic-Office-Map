
package dynamaps.repository.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dynamaps.model.configuration.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Person findByName(String name);
}
