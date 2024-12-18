package com.ManagementSystem.Car.service;

import com.ManagementSystem.Car.model.Car;
import com.ManagementSystem.Car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> getAllCars(String name, String model, Integer year) {
        if (name != null) {
            return carRepository.findByName(name);
        }
        if (model != null) {
            return carRepository.findByModel(model);
        }
        if (year != null) {
            return carRepository.findByYear(year);
        }
        return carRepository.findAll();
    }

    public List<Car> searchCars(String search) {
        return carRepository.findByNameContainingOrModelContainingOrColorContainingOrFuelTypeContaining(search, search, search, search);
    }

    public List<Car> searchCarsByName(String name) {
        return carRepository.findByNameContainingIgnoreCase(name); // Ignore case for a more flexible search
    }


}
