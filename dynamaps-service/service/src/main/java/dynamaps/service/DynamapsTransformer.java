package dynamaps.service;

import dynamaps.dto.DeskDTO;
import dynamaps.dto.FloorDTO;
import dynamaps.dto.PersonDTO;
import dynamaps.dto.ZoneDTO;
import dynamaps.model.configuration.Desk;
import dynamaps.model.configuration.Floor;
import dynamaps.model.configuration.Person;
import dynamaps.model.configuration.Zone;

import java.util.List;

/**
 * Created by caldea on 3/26/2016.
 */
public interface DynamapsTransformer {

    PersonDTO transform(Person person);

    List<PersonDTO> toPersonDtoList(List<Person> person);

    Person transform(PersonDTO personDTO);

    List<Person> toPersonEntityList(List<PersonDTO> personDTO);

    FloorDTO transform(Floor floor);

    List<FloorDTO> toFloorDtoList(List<Floor> floor);

    Floor transform(FloorDTO floorDTO);

    List<Floor> toFloorEntityList(List<FloorDTO> floorDTO);

    DeskDTO transform(Desk desk);

    List<DeskDTO> toDeskDtoList(List<Desk> desk);

    Desk transform(DeskDTO deskDTO);

    List<Desk> toDeskEntityList(List<DeskDTO> deskDTO);

    ZoneDTO transform(Zone zone);

    List<ZoneDTO> toZoneDtoList(List<Zone> zone);

    Zone transform(ZoneDTO zoneDTO);

    List<Zone> toZoneEntityList(List<ZoneDTO> zoneDTO);
}
