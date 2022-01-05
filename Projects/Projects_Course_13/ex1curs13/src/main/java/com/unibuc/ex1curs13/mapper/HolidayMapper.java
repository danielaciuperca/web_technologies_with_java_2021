package com.unibuc.ex1curs13.mapper;

import com.unibuc.ex1curs13.dto.*;
import com.unibuc.ex1curs13.model.*;
import org.springframework.stereotype.*;

@Component
public class HolidayMapper {
    public Holiday holidayRequestToHoliday(HolidayRequest holidayRequest){
        return new Holiday(holidayRequest.getAccommodation(), holidayRequest.getDuration(),
                holidayRequest.getTransportation(), holidayRequest.getPrice(), holidayRequest.getDestinationId());
    }
}
