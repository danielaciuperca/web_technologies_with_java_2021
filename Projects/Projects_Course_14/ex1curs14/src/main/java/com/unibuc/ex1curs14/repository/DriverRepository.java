package com.unibuc.ex1curs14.repository;

import com.unibuc.ex1curs14.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByEmail(String email);

    List<Driver> findByName(String name);

    List<Driver> findByCity(String city);

    List<Driver> findByNameAndCity(String name, String city);
}
