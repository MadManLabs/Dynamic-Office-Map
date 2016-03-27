package dynamaps.service;

import dynamaps.dto.MacDTO;
import dynamaps.dto.ZoneDTO;
import dynamaps.model.configuration.Mac;
import dynamaps.model.configuration.Zone;
import org.springframework.stereotype.Service;

/**
 * Created by caldea on 3/26/2016.
 */
@Service
public class SensorTransformerImpl implements SensorTransformer {


    @Override
    public MacDTO transform(Mac mac) {
        if (mac != null) {
            MacDTO macDTO = new MacDTO();
            macDTO.setValue(mac.getValue());
            macDTO.setZone(transform(mac.getZone()));
            return macDTO;
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
