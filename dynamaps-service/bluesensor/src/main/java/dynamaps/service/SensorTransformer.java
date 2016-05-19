package dynamaps.service;

import dynamaps.dto.DeviceDTO;
import dynamaps.dto.ZoneDTO;
import dynamaps.model.configuration.Device;
import dynamaps.model.configuration.Zone;

/**
 * Created by caldea on 3/26/2016.
 */
public interface SensorTransformer {

    DeviceDTO transform(Device device);

    ZoneDTO transform(Zone zone);

}
