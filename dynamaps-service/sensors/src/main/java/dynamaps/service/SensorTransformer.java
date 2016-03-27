package dynamaps.service;

import dynamaps.dto.MacDTO;
import dynamaps.dto.ZoneDTO;
import dynamaps.model.configuration.Mac;
import dynamaps.model.configuration.Zone;

/**
 * Created by caldea on 3/26/2016.
 */
public interface SensorTransformer {

    MacDTO transform(Mac mac);

    ZoneDTO transform(Zone zone);

}
