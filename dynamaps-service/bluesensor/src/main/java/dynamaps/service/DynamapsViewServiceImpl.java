package dynamaps.service;

import dynamaps.dto.DeviceDTO;
import dynamaps.repository.configuration.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by caldea on 3/26/2016.
 */
@Service
@Transactional
public class DynamapsViewServiceImpl implements DynamapsViewService {

    @Autowired
    private SensorTransformer sensorTransformer;

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public DeviceDTO getDeviceDetails(String value) {

        return sensorTransformer.transform(deviceRepository.findByValue(value));
    }
}
