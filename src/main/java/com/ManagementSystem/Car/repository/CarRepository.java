package com.ManagementSystem.Car.repository;

import com.ManagementSystem.Car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByModel(String model);

    List<Car> findByName(String name);

    List<Car> findByYear(Integer year);

    List<Car> findByNameContainingOrModelContainingOrColorContainingOrFuelTypeContaining(String search, String search1, String search2, String search3);

    List<Car> findByNameContainingIgnoreCase(String name);
}
