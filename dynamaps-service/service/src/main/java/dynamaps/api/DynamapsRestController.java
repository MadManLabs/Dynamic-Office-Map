package dynamaps.api;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


import dynamaps.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${dynamaps.api.root}/${dynamaps.api.version}/${dynamaps.api.find}")
public class DynamapsRestController {

	private static final Logger			LOGGER	= LoggerFactory.getLogger(DynamapsRestController.class);

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/person/{code}")
	@ResponseBody
	public ResponseEntity<PersonDTO> getPersonDetails(@PathVariable("code") final String code) {
		LOGGER.debug("REST call for getting a person by code: " + code);
		PersonDTO personDTO = new PersonDTO();
		personDTO.setName("Cosmin Aldea");
		personDTO.setCode("EN_CALDEA");

		return new ResponseEntity<PersonDTO>(personDTO, OK);
	}
}