package com.unibuc.ex1curs14.controller;

import com.unibuc.ex1curs14.dto.*;
import com.unibuc.ex1curs14.exception.*;
import com.unibuc.ex1curs14.mapper.*;
import com.unibuc.ex1curs14.model.*;
import com.unibuc.ex1curs14.service.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.net.*;
import java.util.*;

@RestController
@RequestMapping("/driver")
@Validated
public class DriverController {
    private DriverService driverService;
    private DriverMapper driverMapper;

    public DriverController(DriverService driverService, DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
    }

    @PostMapping
    public ResponseEntity<Driver> create(
            @Valid
            @RequestBody CreateDriverRequestDto request) {
        Driver driver = driverService.create(driverMapper.createDriverRequestDtoToDriver(request));
        return ResponseEntity.created(URI.create("/driver/" + driver.getId()))
                .body(driver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> update(
        @PathVariable long id,
        @Valid
        @RequestBody UpdateDriverRequestDto request) {
        if(id != request.getId()) {
            throw new InvalidUpdateRequestException();
        }
        return ResponseEntity.ok(driverService.update(driverMapper.updateDriverRequestDtoToDriver(request)));
    }

    @GetMapping
    public List<Driver> get(@RequestParam(required = false) String name,
                            @RequestParam(required = false) String city) {
        return driverService.get(name, city);
    }
}
