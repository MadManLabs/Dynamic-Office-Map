package dynamaps.service;

import dynamaps.dto.DeviceDTO;
import dynamaps.dto.ZoneDTO;
import dynamaps.model.configuration.Device;
import dynamaps.model.configuration.Zone;
import org.springframework.stereotype.Service;

/**
 * Created by caldea on 3/26/2016.
 */
@Service
public class SensorTransformerImpl implements SensorTransformer {


    @Override
    public DeviceDTO transform(Device device) {
        if (device != null) {
            DeviceDTO deviceDTO = new DeviceDTO();
            deviceDTO.setValue(device.getValue());
            deviceDTO.setZone(transform(device.getZone()));
            return deviceDTO;
        } else {
            return null;
        }
    }

    @Override
    public ZoneDTO transform(Zone zone){
        if (zone != null) {
            ZoneDTO zoneDTO = new ZoneDTO();
            zoneDTO.setId(zone.getId());
            zoneDTO.setName(zone.getName());
            return zoneDTO;
        } else {
            return null;
        }
    }

}
