package dynamaps.repository.configuration;

import dynamaps.model.configuration.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by caldea on 3/27/2016.
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Device findByValue(String value);
}
