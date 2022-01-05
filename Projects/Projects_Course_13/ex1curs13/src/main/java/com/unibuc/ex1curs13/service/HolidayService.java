package com.unibuc.ex1curs13.service;

import com.unibuc.ex1curs13.exception.*;
import com.unibuc.ex1curs13.model.*;
import com.unibuc.ex1curs13.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class HolidayService {
	private HolidayRepository holidayRepository;
	private NotificationService notificationService;
	private DestinationService destinationService;

	public HolidayService(HolidayRepository holidayRepository, NotificationService notificationService, DestinationService destinationService) {
		this.holidayRepository = holidayRepository;
		this.notificationService = notificationService;
		this.destinationService = destinationService;
	}

	public Holiday create(Holiday holiday) {
		Optional<Destination> destination = destinationService.getById(holiday.getDestinationId());
		if(destination.isEmpty()) {
			throw new DestinationNotFoundException(holiday.getDestinationId());
		}
		Holiday savedHoliday = holidayRepository.create(holiday);
		notificationService.sendNotificationForVisa(destination.get());
		return savedHoliday;
	}
}
