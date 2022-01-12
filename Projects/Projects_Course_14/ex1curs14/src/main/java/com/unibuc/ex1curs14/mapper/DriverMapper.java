package com.unibuc.ex1curs14.mapper;

import com.unibuc.ex1curs14.dto.*;
import com.unibuc.ex1curs14.model.*;
import org.springframework.stereotype.*;

@Component
public class DriverMapper {

    public Driver createDriverRequestDtoToDriver(CreateDriverRequestDto request) {
        return new Driver(request.getName(), request.getEmail(), request.getCity());
    }

    public Driver updateDriverRequestDtoToDriver(UpdateDriverRequestDto request) {
        return new Driver(request.getId(), request.getName(), request.getEmail(), request.getCity());
    }
}
