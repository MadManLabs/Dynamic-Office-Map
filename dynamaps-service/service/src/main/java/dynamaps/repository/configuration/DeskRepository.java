
package dynamaps.repository.configuration;

import dynamaps.model.configuration.Desk;
import dynamaps.model.configuration.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskRepository extends JpaRepository<Desk, String> {

	Person findByCode(
			String code);
}
