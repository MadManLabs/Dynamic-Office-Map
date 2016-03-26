package dynamaps.service;

import dynamaps.dto.DeskDTO;
import dynamaps.dto.FloorDTO;
import dynamaps.dto.PersonDTO;
import dynamaps.dto.ZoneDTO;

import java.util.List;

/**
 * Created by caldea on 3/26/2016.
 */
public interface DynamapsViewService {


    PersonDTO getPersonDetails(String name);

    List<PersonDTO> getAllPersons();

    PersonDTO savePersonDetails(PersonDTO personDTO);

    FloorDTO getFloorDetails(Integer id);

    List<FloorDTO> getAllFloors();

    FloorDTO saveFloorDetails(FloorDTO floorDTO);

    DeskDTO getDeskDetails(String code);

    List<DeskDTO> getAllDesks();

    DeskDTO saveDeskDetails(DeskDTO deskDTO);

    ZoneDTO getZoneDetails(Integer id);

    List<ZoneDTO> getAllZones();

    List<ZoneDTO> getAllZonesByFloor(Integer floorId);

    ZoneDTO saveZoneDetails(ZoneDTO zoneDTO);
}
