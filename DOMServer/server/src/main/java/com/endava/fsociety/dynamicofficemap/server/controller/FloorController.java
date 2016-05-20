package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.dto.FloorDTO;
import com.endava.fsociety.dynamicofficemap.server.viewservice.FloorViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fstancu on 5/11/2016.
 */

@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorViewService floorViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<FloorDTO> findAll() {
        return floorViewService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FloorDTO findById(@PathVariable String id) {
        return floorViewService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public FloorDTO save(@RequestBody FloorDTO floorDTO) {
        return floorViewService.save(floorDTO);
    }

}
