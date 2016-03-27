package dynamaps.service;

import dynamaps.dto.MacDTO;
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
    private MacRepository macRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public MacDTO getMacDetails(String value) {

        return sensorTransformer.transform(macRepository.findByValue(value));
    }
}
