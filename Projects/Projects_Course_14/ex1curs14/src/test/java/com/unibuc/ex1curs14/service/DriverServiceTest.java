package com.unibuc.ex1curs14.service;

import com.unibuc.ex1curs14.exception.*;
import com.unibuc.ex1curs14.model.*;
import com.unibuc.ex1curs14.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Test
    @DisplayName("Driver is created successfully")
    void create() {
        //arrange
        Driver driver = new Driver("John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByEmail(driver.getEmail()))
                .thenReturn(Optional.empty());
        Driver savedDriver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.save(driver)).thenReturn(savedDriver);

        //act
        Driver result = driverService.create(driver);

        //assert
        assertNotNull(result);
        assertEquals(savedDriver.getId(), result.getId());
        assertEquals(savedDriver.getName(), result.getName());
        assertEquals(savedDriver.getEmail(), result.getEmail());
        assertEquals(savedDriver.getCity(), result.getCity());

        verify(driverRepository).findByEmail(driver.getEmail());
        verify(driverRepository).save(driver);
    }

    @Test
    @DisplayName("Driver email already exists - driver is not created")
    void createThrowsException() {
        //arrange
        Driver driver = new Driver("John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByEmail(driver.getEmail()))
                .thenReturn(Optional.of(driver));

        //act
        DriverAlreadyExistsException exception = assertThrows(DriverAlreadyExistsException.class,
                () -> driverService.create(driver));

        //assert
        assertNotNull(exception);
        assertEquals("There is already a driver with the same email.",
                exception.getMessage());

        verify(driverRepository).findByEmail(driver.getEmail());
        verify(driverRepository, times(0)).save(driver);
    }

    @Test
    @DisplayName("Driver name and city are updated successfully")
    void updateNameAndCity() {
        //arrange
        Driver oldDriver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        Driver newDriver = new Driver(1, "Jonathan", "john@gmail.com", "Brasov");
        when(driverRepository.findById(newDriver.getId()))
                .thenReturn(Optional.of(oldDriver));
        when(driverRepository.save(newDriver)).thenReturn(newDriver);

        //act
        Driver result = driverService.update(newDriver);

        //assert
        assertNotNull(result);
        assertEquals(newDriver.getId(), result.getId());
        assertEquals(newDriver.getName(), result.getName());
        assertEquals(newDriver.getEmail(), result.getEmail());
        assertEquals(newDriver.getCity(), result.getCity());

        verify(driverRepository).findById(newDriver.getId());
        verify(driverRepository).save(newDriver);
        verify(driverRepository, never()).findByEmail(newDriver.getEmail());
    }

    @Test
    @DisplayName("Driver email is updated successfully")
    void updateEmail() {
        //arrange
        Driver oldDriver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        Driver newDriver = new Driver(1, "John", "johnny@gmail.com", "Bucharest");
        when(driverRepository.findById(newDriver.getId()))
                .thenReturn(Optional.of(oldDriver));
        when(driverRepository.findByEmail(newDriver.getEmail()))
                .thenReturn(Optional.empty());
        when(driverRepository.save(newDriver))
                .thenReturn(newDriver);

        //act
        Driver result = driverService.update(newDriver);

        //assert
        assertNotNull(result);
        assertEquals(newDriver.getId(), result.getId());
        assertEquals(newDriver.getName(), result.getName());
        assertEquals(newDriver.getEmail(), result.getEmail());
        assertEquals(newDriver.getCity(), result.getCity());

        verify(driverRepository).findById(newDriver.getId());
        verify(driverRepository).findByEmail(newDriver.getEmail());
        verify(driverRepository).save(newDriver);
    }

    @Test
    @DisplayName("Driver email cannot be updated - new email belongs to other driver")
    void updateEmailThrowsException() {
        //arrange
        Driver oldDriver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        Driver newDriver = new Driver(1, "John", "johnny@gmail.com", "Bucharest");
        Driver anotherDriver = new Driver(2, "Billy John", "johnny@gmail.com", "Iasi");
        when(driverRepository.findById(newDriver.getId()))
                .thenReturn(Optional.of(oldDriver));
        when(driverRepository.findByEmail(newDriver.getEmail()))
                .thenReturn(Optional.of(anotherDriver));

        //act
        DriverAlreadyExistsException exception = assertThrows(DriverAlreadyExistsException.class,
                () -> driverService.update(newDriver));

        //assert
        assertNotNull(exception);
        assertEquals("There is already a driver with the same email.",
                exception.getMessage());

        verify(driverRepository).findById(newDriver.getId());
        verify(driverRepository).findByEmail(newDriver.getEmail());
        verify(driverRepository, times(0)).save(newDriver);
    }

    @Test
    @DisplayName("Get drivers by name")
    void getByName() {
        //arrange
        String name = "John";
        Driver driver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByName(name))
                .thenReturn(List.of(driver));

        //act
        List<Driver> result = driverService.get(name, null);

        //assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driver, result.get(0));

        verify(driverRepository).findByName(name);
        verify(driverRepository, never()).findByNameAndCity(any(), any());
        verify(driverRepository, never()).findByCity(any());
        verify(driverRepository, never()).findAll();
    }

    @Test
    @DisplayName("Get drivers by city")
    void getByCity() {
        //arrange
        String city = "Bucharest";
        Driver driver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByCity(city))
                .thenReturn(List.of(driver));

        //act
        List<Driver> result = driverService.get(null, city);

        //assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driver, result.get(0));

        verify(driverRepository, never()).findByName(any());
        verify(driverRepository, never()).findByNameAndCity(any(), any());
        verify(driverRepository).findByCity(city);
        verify(driverRepository, never()).findAll();
    }

    @Test
    @DisplayName("Get drivers by name and city")
    void getByNameAndCity() {
        //arrange
        String name = "John";
        String city = "Bucharest";
        Driver driver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.findByNameAndCity(name, city))
                .thenReturn(List.of(driver));

        //act
        List<Driver> result = driverService.get(name, city);

        //assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driver, result.get(0));

        verify(driverRepository, never()).findByName(any());
        verify(driverRepository).findByNameAndCity(name, city);
        verify(driverRepository, never()).findByCity(any());
        verify(driverRepository, never()).findAll();
    }

    @Test
    @DisplayName("Get all drivers - no filters by name and city")
    void getAll() {
        //arrange
        Driver driver = new Driver(1, "John", "john@gmail.com", "Bucharest");
        when(driverRepository.findAll()).thenReturn(List.of(driver));

        //act
        List<Driver> result = driverService.get(null, null);

        //assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(driver, result.get(0));

        verify(driverRepository, never()).findByName(any());
        verify(driverRepository, never()).findByNameAndCity(any(), any());
        verify(driverRepository, never()).findByCity(any());
        verify(driverRepository).findAll();
    }
}