package dynamaps.service;

import dynamaps.dto.DeskDTO;
import dynamaps.dto.FloorDTO;
import dynamaps.dto.PersonDTO;
import dynamaps.dto.ZoneDTO;
import dynamaps.model.configuration.Desk;
import dynamaps.model.configuration.Floor;
import dynamaps.model.configuration.Person;
import dynamaps.model.configuration.Zone;
import dynamaps.repository.configuration.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by caldea on 3/26/2016.
 */
@Service
@Transactional
public class DynamapsViewServiceImpl implements DynamapsViewService {

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


    @Override
    public PersonDTO getPersonDetails(String name) {
        return dynamapsTransformer.transform(personRepository.findByName(name));
    }

    @Override
    public PersonDTO getPersonDetails(Integer id) {
        return dynamapsTransformer.transform(personRepository.findOne(id));
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        return dynamapsTransformer.toPersonDtoList(personRepository.findAll());
    }

    @Override
    public PersonDTO savePersonDetails(PersonDTO personDTO) {
        Person existingPerson = null;
        Desk existingDesk = null;
        Zone existingZone = null;
        if (personDTO.getDesk() != null && personDTO.getDesk().getId() != null) {
            existingDesk = deskRepository.findOne(personDTO.getDesk().getId());
        }
        if (personDTO.getZone() != null && personDTO.getZone().getId() != null) {
            existingZone = zoneRepository.findOne(personDTO.getZone().getId());
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
        return dynamapsTransformer.toFloorDtoList(floorRepository.findAll());
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
}
