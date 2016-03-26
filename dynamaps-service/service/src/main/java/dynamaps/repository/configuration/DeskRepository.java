
package dynamaps.repository.configuration;

import dynamaps.model.configuration.Desk;
import dynamaps.model.configuration.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Integer> {
    Desk findByCode(String code);

    List<Desk> findByZoneId(Integer id);

    List<Desk> findByZoneFloorId(Integer id);
}
