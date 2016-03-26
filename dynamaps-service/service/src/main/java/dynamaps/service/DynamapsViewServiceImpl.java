package dynamaps.service;

import dynamaps.dto.DeskDTO;
import dynamaps.dto.FloorDTO;
import dynamaps.dto.PersonDTO;
import dynamaps.model.configuration.Floor;
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


    @Override
    public PersonDTO getPersonDetails(String name) {
        return dynamapsTransformer.transform(personRepository.findByName(name));
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        return dynamapsTransformer.toPersonDtoList(personRepository.findAll());
    }

    @Override
    public PersonDTO savePersonDetails(PersonDTO personDTO) {
        return dynamapsTransformer.transform(personRepository.save(dynamapsTransformer.transform(personDTO)));
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
        return dynamapsTransformer.transform(deskRepository.save(dynamapsTransformer.transform(deskDTO)));
    }
}
