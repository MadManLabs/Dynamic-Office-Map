package dynamaps.api;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import dynamaps.dto.MacDTO;
import dynamaps.service.DynamapsViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${dynamaps.api.root}/${dynamaps.api.version}/${dynamaps.api.find}")
public class SensorsRestController {

	private static final Logger			LOGGER	= LoggerFactory.getLogger(SensorsRestController.class);

	@Autowired
	private DynamapsViewService dynamapsViewService;

	@RequestMapping(produces = APPLICATION_JSON_VALUE, method = GET, value = "/mac/{code}")
	@ResponseBody
	public ResponseEntity<MacDTO> getPersonDetails(@PathVariable("code") final String code) {
		LOGGER.debug("REST call for getting a person by value: " + code);
		MacDTO macDTO = dynamapsViewService.getMacDetails(code);

		return new ResponseEntity<MacDTO>(macDTO, OK);
	}
}