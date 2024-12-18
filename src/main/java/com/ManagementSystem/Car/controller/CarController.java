package com.ManagementSystem.Car.controller;

import com.ManagementSystem.Car.model.Car;
import com.ManagementSystem.Car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;



    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year) {

        List<Car> cars = carService.getAllCars(name, model, year);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCars(@RequestParam(required = false) String search) {
        List<Car> cars = carService.searchCars(search);
        return ResponseEntity.ok(cars);
    }


    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }


    // filter records by name
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Car>> searchCarsByName(@PathVariable String name) {
        List<Car> cars = carService.searchCarsByName(name);
        return ResponseEntity.ok(cars);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        return carService.getCarById(id)
                .map(car -> {
                    car.setName(carDetails.getName());
                    car.setModel(carDetails.getModel());
                    car.setYear(carDetails.getYear());
                    car.setPrice(carDetails.getPrice());
                    car.setColor(carDetails.getColor());
                    car.setFuelType(carDetails.getFuelType());
                    return ResponseEntity.ok(carService.saveCar(car));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        if (carService.getCarById(id).isPresent()) {
            carService.deleteCar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
