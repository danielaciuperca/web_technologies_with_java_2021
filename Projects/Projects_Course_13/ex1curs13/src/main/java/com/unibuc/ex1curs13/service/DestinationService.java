package com.unibuc.ex1curs13.service;

import com.unibuc.ex1curs13.exception.*;
import com.unibuc.ex1curs13.model.*;
import com.unibuc.ex1curs13.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class DestinationService {
	private DestinationRepository destinationRepository;

	public DestinationService(DestinationRepository destinationRepository) {
		this.destinationRepository = destinationRepository;
	}

	public Destination create(Destination destination) {
		Optional<Destination> existingDestinationSameName = destinationRepository.getByName(destination.getName());
		existingDestinationSameName.ifPresent(e -> {
			throw new DuplicateDestinationException();
		});
		return destinationRepository.create(destination);
	}

	public Optional<Destination> getById(long id) {
		return destinationRepository.getById(id);
	}
}
