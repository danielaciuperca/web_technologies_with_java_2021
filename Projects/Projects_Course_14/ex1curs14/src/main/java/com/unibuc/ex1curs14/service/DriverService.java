package com.unibuc.ex1curs14.service;

import com.unibuc.ex1curs14.exception.*;
import com.unibuc.ex1curs14.model.*;
import com.unibuc.ex1curs14.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class DriverService {

    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver create(Driver driver) {
        checkUniqueEmail(driver);
        return driverRepository.save(driver);
    }

    public Driver update(Driver driver) {
        Driver existingDriver = driverRepository.findById(driver.getId())
                .orElseThrow(() -> new DriverNotFoundException());
        if(!driver.getEmail().equals(existingDriver.getEmail())) {
            checkUniqueEmail(driver);
        }
        return  driverRepository.save(driver);
    }

    public List<Driver> get(String name, String city) {
        if(name != null) {
            if(city != null) {
                return driverRepository.findByNameAndCity(name,city);
            }
            return driverRepository.findByName(name);
        }
        if(city != null) {
            return driverRepository.findByCity(city);
        }
        return driverRepository.findAll();
    }

    private void checkUniqueEmail(Driver driver) {
        Optional<Driver> existingDriver = driverRepository.findByEmail(driver.getEmail());
        if (existingDriver.isPresent()) {
            throw new DriverAlreadyExistsException();
        }
    }


}
