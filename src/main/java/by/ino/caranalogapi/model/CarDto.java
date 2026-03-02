package by.ino.caranalogapi.model;

import lombok.Data;

import java.util.List;

@Data
public class CarDto {
    Long id;
    String url;
    String brand;
    String model;
    String excerpt;
    String modification;
    Integer year;
    String color;
    Integer mileageKm;
    String condition;
    String engineType;
    String fuelType;
    Integer powerKw;
    String engineFullText;
    Integer volume;
    String transmission;
    String carClass;
    String bodyType;
    Integer doors;
    Integer seats;
    Integer curbWeightKg;
    Integer wheelbaseMm;
    String tireSizeFront;
    String tireSizeRear;
    Double price;
    String country;
    List<Long> photoIds;
}
