package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.model.*;
import com.unibuc.ex1curs11.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.test.context.*;

import java.util.*;

import static com.unibuc.ex1curs11.model.DestinationType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DestinationServiceIT {

    @Autowired
    private DestinationService destinationService;
    @MockBean
    private DestinationRepository destinationRepository;

    @Test
    @DisplayName("Create destination - happy flow")
    public void createDestinationHappyFlow() {
        Destination destination = new Destination("New York", "USA", CITY);
        Destination savedDestination = new Destination(1, "New York", "USA", CITY);
        when(destinationRepository.findByName(destination.getName())).thenReturn(Optional.empty());
        when(destinationRepository.save(destination)).thenReturn(savedDestination);

        Destination result = destinationService.create(destination);

        assertNotNull(result.getId());
        assertEquals(savedDestination.getId(), result.getId());
        assertEquals(savedDestination.getName(), result.getName());
        assertEquals(savedDestination.getCountry(), result.getCountry());
        assertEquals(savedDestination.getDestinationType(), result.getDestinationType());
    }

}
