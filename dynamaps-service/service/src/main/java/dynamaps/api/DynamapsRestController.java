package dynamaps.api;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


import dynamaps.dto.DeskDTO;
import dynamaps.dto.FloorDTO;
import dynamaps.dto.PersonDTO;
import dynamaps.dto.ZoneDTO;
import dynamaps.service.DynamapsViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${dynamaps.api.root}/${dynamaps.api.version}/${dynamaps.api.find}")
public class DynamapsRestController {

	private static final Logger			LOGGER	= LoggerFactory.getLogger(DynamapsRestController.class);

	@Autowired
	private DynamapsViewService dynamapsViewService;

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/person/{code}")
	@ResponseBody
	public ResponseEntity<PersonDTO> getPersonDetails(@PathVariable("code") final String code) {
		LOGGER.debug("REST call for getting a person by name: " + code);
		PersonDTO personDTO = dynamapsViewService.getPersonDetails(code);

		return new ResponseEntity<PersonDTO>(personDTO, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/person/byid/{code}")
	@ResponseBody
	public ResponseEntity<PersonDTO> getPersonDetails(@PathVariable("code") final Integer code) {
		LOGGER.debug("REST call for getting a person by id: " + code);
		PersonDTO personDTO = dynamapsViewService.getPersonDetails(code);

		return new ResponseEntity<PersonDTO>(personDTO, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/person")
	@ResponseBody
	public ResponseEntity<List<PersonDTO>> getAllPersonDetails() {

		List<PersonDTO> allPersons = dynamapsViewService.getAllPersons();

		return new ResponseEntity<List<PersonDTO>>(allPersons, OK);
	}

	@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = POST, value = "/person")
	@ResponseBody
	public ResponseEntity<?> savePersonDetails(@RequestBody final PersonDTO personDTO) {
		LOGGER.debug("Saving person" + personDTO);
		PersonDTO saved = dynamapsViewService.savePersonDetails(personDTO);
		return new ResponseEntity<Object>(saved, OK);
	}

	@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = PUT, value = "/person/{code}")
	@ResponseBody
	public ResponseEntity<?> updatePersonDetails(@RequestBody final PersonDTO personDTO, @PathVariable("code") final String code) {
		LOGGER.debug("updating person with code " + code);
		PersonDTO saved = dynamapsViewService.savePersonDetails(personDTO);
		return new ResponseEntity<Object>(saved, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/floor/{code}")
	@ResponseBody
	public ResponseEntity<FloorDTO> getFloorDetails(@PathVariable("code") final Integer id) {
		LOGGER.debug("REST call for getting a floor by id: " + id);
		FloorDTO floorDTO = dynamapsViewService.getFloorDetails(id);

		return new ResponseEntity<FloorDTO>(floorDTO, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/floor")
	@ResponseBody
	public ResponseEntity<List<FloorDTO>> getAllFloorDetails() {

		List<FloorDTO> floorDTOs = dynamapsViewService.getAllFloors();

		return new ResponseEntity<List<FloorDTO>>(floorDTOs, OK);
	}

	@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = POST, value = "/floor")
	@ResponseBody
	public ResponseEntity<?> saveFloorDetails(@RequestBody final FloorDTO floorDTO) {
		LOGGER.debug("Saving floor " + floorDTO);
		FloorDTO saved = dynamapsViewService.saveFloorDetails(floorDTO);
		return new ResponseEntity<Object>(saved, OK);
	}

	@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = PUT, value = "/floor/{code}")
	@ResponseBody
	public ResponseEntity<?> updateFloorDetails(@RequestBody final FloorDTO floorDTO, @PathVariable("code") final Integer code) {
		LOGGER.debug("updating floor with code " + code);
		FloorDTO saved = dynamapsViewService.saveFloorDetails(floorDTO);
		return new ResponseEntity<Object>(saved, OK);
	}


	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/desk")
	@ResponseBody
	public ResponseEntity<List<DeskDTO>> getAllDeskDetails() {

		List<DeskDTO> deskDTOs = dynamapsViewService.getAllDesks();

		return new ResponseEntity<List<DeskDTO>>(deskDTOs, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/desk/{code}")
	@ResponseBody
	public ResponseEntity<DeskDTO> getDeskDetails(@PathVariable("code") final String code) {
		LOGGER.debug("REST call for getting desk by code: " + code);
		DeskDTO deskDTO = dynamapsViewService.getDeskDetails(code);

		return new ResponseEntity<DeskDTO>(deskDTO, OK);
	}

	@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = POST, value = "/desk")
	@ResponseBody
	public ResponseEntity<?> saveDeskDetails(@RequestBody final DeskDTO deskDTO) {
		LOGGER.debug("Saving desk " + deskDTO);
		DeskDTO saved = dynamapsViewService.saveDeskDetails(deskDTO);
		return new ResponseEntity<Object>(saved, OK);
	}

	@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = PUT, value = "/desk/{code}")
	@ResponseBody
	public ResponseEntity<?> updateDeskDetails(@RequestBody final DeskDTO deskDTO, @PathVariable("code") final String code) {
		LOGGER.debug("updating desk with code " + code);
		DeskDTO saved = dynamapsViewService.saveDeskDetails(deskDTO);
		return new ResponseEntity<Object>(saved, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/zone/{code}")
	@ResponseBody
	public ResponseEntity<ZoneDTO> getZoneDetails(@PathVariable("code") final Integer code) {
		LOGGER.debug("REST call for getting desk by name: " + code);
		ZoneDTO zoneDTO = dynamapsViewService.getZoneDetails(code);

		return new ResponseEntity<ZoneDTO>(zoneDTO, OK);
	}

	@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = POST, value = "/zone")
	@ResponseBody
	public ResponseEntity<?> saveZoneDetails(@RequestBody final ZoneDTO zoneDTO) {
		LOGGER.debug("Saving zone " + zoneDTO);
		ZoneDTO saved = dynamapsViewService.saveZoneDetails(zoneDTO);
		return new ResponseEntity<Object>(saved, OK);
	}

	@RequestMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = PUT, value = "/zone/{code}")
	@ResponseBody
	public ResponseEntity<?> updateZoneDetails(@RequestBody final ZoneDTO zoneDTO, @PathVariable("code") final Integer code) {
		LOGGER.debug("updating desk with code " + code);
		ZoneDTO saved = dynamapsViewService.saveZoneDetails(zoneDTO);
		return new ResponseEntity<Object>(saved, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/floor/{id}/zone")
	@ResponseBody
	public ResponseEntity<List<ZoneDTO>> getAllZoneDetailsByFloor(@PathVariable("id") final Integer id) {

		List<ZoneDTO> zoneDTOs = dynamapsViewService.getAllZonesByFloor(id);

		return new ResponseEntity<List<ZoneDTO>>(zoneDTOs, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/zone")
	@ResponseBody
	public ResponseEntity<List<ZoneDTO>> getAllZonesDetails() {

		List<ZoneDTO> zoneDTOs = dynamapsViewService.getAllZones();

		return new ResponseEntity<List<ZoneDTO>>(zoneDTOs, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/zone/{id}/desk")
	@ResponseBody
	public ResponseEntity<List<DeskDTO>> getAllDeskDetailsByZone(@PathVariable("id") final Integer id) {

		List<DeskDTO> deskDTOs = dynamapsViewService.getAllDesksByZone(id);

		return new ResponseEntity<List<DeskDTO>>(deskDTOs, OK);
	}

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/floor/{id}/desk")
	@ResponseBody
	public ResponseEntity<List<DeskDTO>> getAllDeskDetailsByFloor(@PathVariable("id") final Integer id) {

		List<DeskDTO> deskDTOs = dynamapsViewService.getAllDesksByFloor(id);

		return new ResponseEntity<List<DeskDTO>>(deskDTOs, OK);
	}


}