package com.ManagementSystem.Car.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Car") // This should match the table name
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "price")
    private Double price;

    @Column(name = "color")
    private String color;

    @Column(name = "fuelType")
    private String fuelType;
}
