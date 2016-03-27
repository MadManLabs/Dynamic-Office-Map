package dynamaps.service;

import dynamaps.dto.DeskDTO;
import dynamaps.dto.FloorDTO;
import dynamaps.dto.PersonDTO;
import dynamaps.dto.ZoneDTO;
import dynamaps.exceptions.DeskOccupiedException;

import java.util.List;

/**
 * Created by caldea on 3/26/2016.
 */
public interface DynamapsViewService {


    PersonDTO getPersonDetails(String name);

    PersonDTO getPersonDetails(Integer id);

    List<PersonDTO> getAllPersons();

    PersonDTO savePersonDetails(PersonDTO personDTO) throws DeskOccupiedException;

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

    List<DeskDTO> getAllDesksByZone(Integer zoneId);

    List<DeskDTO> getAllDesksByFloor(Integer floorId);

    List<PersonDTO> getAllPersonsByFloor(Integer floorId);

    List<PersonDTO> getAllPersonsByZone(Integer zoneId);

    PersonDTO getPersonByDesk(Integer deskId);
}
