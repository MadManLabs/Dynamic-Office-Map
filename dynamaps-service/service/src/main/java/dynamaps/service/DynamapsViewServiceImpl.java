package dynamaps.service;

import dynamaps.dto.*;
import dynamaps.exceptions.DeskOccupiedException;
import dynamaps.model.configuration.Desk;
import dynamaps.model.configuration.Floor;
import dynamaps.model.configuration.Person;
import dynamaps.model.configuration.Zone;
import dynamaps.repository.configuration.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

/**
 * Created by caldea on 3/26/2016.
 */
@Service
@Transactional
public class DynamapsViewServiceImpl implements DynamapsViewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamapsViewServiceImpl.class);

    @Autowired
    private DynamapsTransformer dynamapsTransformer;

    @Autowired
    private DeskRepository deskRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Value("${sensor.mac.url}")
    public String sensorsUrl;

    private RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Override
    public PersonDTO getPersonDetails(String name) {
        PersonDTO personDTO =  dynamapsTransformer.transform(personRepository.findByName(name));
        if (personDTO.getMac() != null) {
            try {
                final HttpHeaders headers = getHttpHeaders();
                final HttpEntity request = new HttpEntity(headers);
                String getMacUrl = sensorsUrl.concat(personDTO.getMac());
                final HttpEntity<String> macInformation = restTemplate.exchange(getMacUrl, GET, request, String.class);
                MacDTO macDto = OBJECT_MAPPER.readValue(macInformation.getBody(), new TypeReference<MacDTO>() {});
                if (macDto != null && macDto.getZone() != null && macDto.getZone().getName() != null) {

                    personDTO.setMacZone(dynamapsTransformer.transform(zoneRepository.findByName(macDto.getZone().getName())));
                }
            } catch (RestClientException | IOException e) {
                LOGGER.debug("Tried to get location for Mac "+ personDTO.getMac() + " but received and error " + e);
            }
        }
        return personDTO;
    }

    @Override
    public PersonDTO getPersonDetails(Integer id) {
        PersonDTO personDTO = dynamapsTransformer.transform(personRepository.findOne(id));
        if (personDTO.getMac() != null) {
            try {
                final HttpHeaders headers = getHttpHeaders();
                final HttpEntity request = new HttpEntity(headers);
                String getMacUrl = sensorsUrl.concat(personDTO.getMac());
                final HttpEntity<String> macInformation = restTemplate.exchange(getMacUrl, GET, request, String.class);
                MacDTO macDto = OBJECT_MAPPER.readValue(macInformation.getBody(), new TypeReference<MacDTO>() {});
                if (macDto != null && macDto.getZone() != null && macDto.getZone().getName() != null) {

                    personDTO.setMacZone(dynamapsTransformer.transform(zoneRepository.findByName(macDto.getZone().getName())));
                }
            } catch (RestClientException | IOException e) {
                LOGGER.debug("Tried to get location for Mac "+ personDTO.getMac() + " but received and error " + e);
            }
        }

        return personDTO;
    }

    private HttpHeaders getHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        return dynamapsTransformer.toPersonDtoList(personRepository.findAll());
    }

    @Override
    public PersonDTO savePersonDetails(PersonDTO personDTO) throws DeskOccupiedException {
        Person existingPerson = null;
        Desk existingDesk = null;
        Zone existingZone = null;
        if (personDTO.getDesk() != null && personDTO.getDesk().getId() != null) {
            existingDesk = deskRepository.findOne(personDTO.getDesk().getId());
        }
        if (personDTO.getZone() != null && personDTO.getZone().getId() != null) {
            existingZone = zoneRepository.findOne(personDTO.getZone().getId());
        }
        if (existingDesk != null) {
            Person occperson = personRepository.findByDesk(existingDesk);
            if (occperson!= null && !occperson.getId().equals(personDTO.getId())) {
                throw new DeskOccupiedException("Occupied by:" + occperson.getId());
            }
        }

        if (personDTO.getId() != null) {
            existingPerson = personRepository.findOne(personDTO.getId());
            if (existingPerson != null) {

                if (existingDesk != null) {
                    existingPerson.setDesk(existingDesk);
                } else if (existingPerson.getDesk() == null) {
                    existingPerson.setDesk(deskRepository.save(new Desk("NO_DESK", "", "")));
                }
                if (existingZone != null) {
                    existingPerson.setZone(existingZone);
                }
                if (personDTO.getName() != null) {
                    existingPerson.setName(personDTO.getName());
                }
                if (personDTO.getEmail() != null) {
                    existingPerson.setEmail(personDTO.getEmail());
                }
                if (personDTO.getMac() != null) {
                    existingPerson.setMac(personDTO.getMac());
                }
                return dynamapsTransformer.transform(personRepository.save(existingPerson));
            } else {
                Person newPerson = dynamapsTransformer.transform(personDTO);
                if (existingDesk != null) {
                    newPerson.setDesk(existingDesk);
                } else {
                    newPerson.setDesk(deskRepository.save(new Desk("NO_DESK", "", "")));
                }
                if (existingZone != null) {
                    newPerson.setZone(existingZone);
                }
                return dynamapsTransformer.transform(personRepository.save(newPerson));
            }
        } else {
            Person newPerson = dynamapsTransformer.transform(personDTO);
            if (existingDesk != null) {
                newPerson.setDesk(existingDesk);
            } else {
                newPerson.setDesk(deskRepository.save(new Desk("NO_DESK", "", "")));
            }
            if (existingZone != null) {
                newPerson.setZone(existingZone);
            }
            return dynamapsTransformer.transform(personRepository.save(newPerson));
        }
    }

    @Override
    public FloorDTO getFloorDetails(Integer id) {
        return dynamapsTransformer.transform(floorRepository.findOne(id));
    }

    @Override
    public List<FloorDTO> getAllFloors() {
        List<FloorDTO> list = dynamapsTransformer.toFloorDtoList(floorRepository.findAll());
        for (FloorDTO floorDTO : list) {
            List<PersonDTO> personDTOs = getAllPersonsByFloor(floorDTO.getId());
            if (personDTOs != null) {
                floorDTO.setNrPersons(personDTOs.size());
            }
        }
        return list;
    }

    @Override
    public FloorDTO saveFloorDetails(FloorDTO floorDTO) {
        Floor existingFloor = null;
        if (floorDTO.getId() != null) {
            existingFloor = floorRepository.findOne(floorDTO.getId());
            if (existingFloor != null) {
                if (floorDTO.getName() != null) {
                    existingFloor.setName(floorDTO.getName());
                }
                if (floorDTO.getMap() != null) {
                    existingFloor.setMap(floorDTO.getMap());
                }
                return dynamapsTransformer.transform(floorRepository.save(existingFloor));
            } else {
                return dynamapsTransformer.transform(floorRepository.save(dynamapsTransformer.transform(floorDTO)));
            }
        } else {
            return dynamapsTransformer.transform(floorRepository.save(dynamapsTransformer.transform(floorDTO)));
        }
    }

    @Override
    public DeskDTO getDeskDetails(String code) {
        return dynamapsTransformer.transform(deskRepository.findByCode(code));
    }

    @Override
    public List<DeskDTO> getAllDesks() {
        return dynamapsTransformer.toDeskDtoList(deskRepository.findAll());
    }

    @Override
    public DeskDTO saveDeskDetails(DeskDTO deskDTO) {
        Desk existingDesk = null;
        Zone existingZone = null;

        if (deskDTO.getZone() != null && deskDTO.getZone().getId() != null) {
            existingZone = zoneRepository.findOne(deskDTO.getZone().getId());
        }

        if (deskDTO.getId() != null) {
            existingDesk = deskRepository.findOne(deskDTO.getId());
            if (existingDesk != null) {

                if (existingZone != null) {
                    existingDesk.setZone(existingZone);
                }
                if (deskDTO.getCode() != null) {
                    existingDesk.setCode(deskDTO.getCode());
                }
                if (deskDTO.getXlayout() != null) {
                    existingDesk.setXlayout(deskDTO.getXlayout());
                }
                if (deskDTO.getYlayout() != null) {
                    existingDesk.setYlayout(deskDTO.getXlayout());
                }
                return dynamapsTransformer.transform(deskRepository.save(existingDesk));
            } else {
                Desk newDesk = dynamapsTransformer.transform(deskDTO);
                if (newDesk != null) {
                    newDesk.setZone(existingZone);
                }

                return dynamapsTransformer.transform(deskRepository.save(newDesk));
            }
        } else {
            Desk newDesk = dynamapsTransformer.transform(deskDTO);
            if (existingZone != null) {
                newDesk.setZone(existingZone);
            }
            return dynamapsTransformer.transform(deskRepository.save(newDesk));
        }
    }

    @Override
    public ZoneDTO getZoneDetails(Integer id) {
        return dynamapsTransformer.transform(zoneRepository.findOne(id));
    }

    @Override
    public List<ZoneDTO> getAllZones() {
        return dynamapsTransformer.toZoneDtoList(zoneRepository.findAll());
    }

    @Override
    public List<ZoneDTO> getAllZonesByFloor(Integer floorId) {
        return dynamapsTransformer.toZoneDtoList(zoneRepository.findByFloorId(floorId));
    }

    @Override
    public ZoneDTO saveZoneDetails(ZoneDTO zoneDTO) {
        Zone existingZone = null;
        if (zoneDTO.getFloor() != null && zoneDTO.getFloor().getId() != null) {
            Floor existingFloor = floorRepository.findOne(zoneDTO.getFloor().getId());
            if (existingFloor != null) {
                if (zoneDTO.getId() != null) {
                    existingZone = zoneRepository.findOne(zoneDTO.getId());
                    if (existingZone != null) {
                        existingZone.setFloor(existingFloor);
                        existingZone.setName(zoneDTO.getName());
                        return dynamapsTransformer.transform(zoneRepository.save(existingZone));
                    } else {
                        Zone newZone = dynamapsTransformer.transform(zoneDTO);
                        newZone.setFloor(existingFloor);
                        return dynamapsTransformer.transform(zoneRepository.save(newZone));
                    }
                } else {
                    Zone newZone = dynamapsTransformer.transform(zoneDTO);
                    newZone.setFloor(existingFloor);
                    return dynamapsTransformer.transform(zoneRepository.save(newZone));
                }
            } else {
                return null;
            }
        } else {
            if (zoneDTO.getId() != null) {
                existingZone = zoneRepository.findOne(zoneDTO.getId());
                if (existingZone != null) {
                    existingZone.setName(zoneDTO.getName());
                    return dynamapsTransformer.transform(zoneRepository.save(existingZone));
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    @Override
    public List<DeskDTO> getAllDesksByZone(Integer zoneId) {
        return dynamapsTransformer.toDeskDtoList(deskRepository.findByZoneId(zoneId));
    }

    @Override
    public List<DeskDTO> getAllDesksByFloor(Integer floorId) {
        return dynamapsTransformer.toDeskDtoList(deskRepository.findByZoneFloorId(floorId));
    }

    @Override
    public List<PersonDTO> getAllPersonsByFloor(Integer floorId) {
        return dynamapsTransformer.toPersonDtoList(personRepository.findByDeskZoneFloorId(floorId));
    }

    @Override
    public List<PersonDTO> getAllPersonsByZone(Integer zoneId) {
        Zone zone = zoneRepository.findOne(zoneId);
        if (zone == null) {
            return new ArrayList<>();
        }
        List<Desk> desks = zone.getDesks();
        List<Person> persons = new ArrayList<>();
        for (Desk desk : desks) {
            Person person = personRepository.findByDesk(desk);
            if (person != null) {
                persons.add(person);
            }
        }
        return dynamapsTransformer.toPersonDtoList(persons);
    }

    @Override
    public PersonDTO getPersonByDesk(Integer deskId) {
        Desk desk = deskRepository.findOne(deskId);
        if (desk == null) {
            return null;
        }
        Person person = personRepository.findByDesk(desk);
        if (person == null) {
            return null;
        }
        return dynamapsTransformer.transform(person);
    }

    @Override
    public PersonDTO updateTmpZone(Integer personId, Integer zoneId) {
        Person person = personRepository.findOne(personId);
        Zone zone = zoneRepository.findOne(zoneId);
        if (person != null && zone != null) {
            person.setZone(zone);
        }
        person = personRepository.save(person);
        return dynamapsTransformer.transform(person);
    }
}
