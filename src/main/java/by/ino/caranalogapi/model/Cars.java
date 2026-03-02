package by.ino.caranalogapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cars", schema = "public")
public class Cars {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "url")
    String url;

    // Марка/Производитель
    @Column(name = "make")
    String make;

    // Основное имя модели
    @Column(name = "model")
    String model;

    // Описание
    @Column(name = "excerpt")
    String excerpt;

    // Название комплектации/версии
    @Column(name = "trim_level")
    String trimLevel;

    // Год выпуска
    @Column(name = "year")
    Integer year;

    // Цвет автомобиля
    @Column(name = "color")
    String color;

    // Пробег в километрах
    @Column(name = "mileage_km")
    Integer mileageKm;

    // Состояние: новый или б/у
    @Column(name = "condition")
    String condition;

    // Тип двигателя
    @Column(name = "engine_type")
    String engineType;

    // Тип топлива
    @Column(name = "fuel_type")
    String fuelType;

    // Максимальная мощность в кВт
    @Column(name = "power_kw")
    Integer powerKw;

    // Полная текстовая строка двигателя
    @Column(name = "engine_full_text")
    String engineFullText;

    // Рабочий объём двигателя в см³
    @Column(name = "displacement_cc")
    Integer displacementCc;

    // Тип КПП (автомат, механика, робот)
    @Column(name = "gearbox_type")
    String gearboxType;

    // Класс автомобиля (SUV, Sedan и т.д.)
    @Column(name = "car_class")
    String carClass;

    // Тип кузова
    @Column(name = "body_type")
    String bodyType;

    // Количество дверей
    @Column(name = "doors")
    Integer doors;

    // Количество мест
    @Column(name = "seats")
    Integer seats;

    // Снаряжённая масса в кг
    @Column(name = "curb_weight_kg")
    Integer curbWeightKg;

    // Колёсная база в мм
    @Column(name = "wheelbase_mm")
    Integer wheelbaseMm;

    // Размерность передних шин
    @Column(name = "tire_size_front")
    String tireSizeFront;

    // Размерность задних шин
    @Column(name = "tire_size_rear")
    String tireSizeRear;

    // Цена автомобиля
    @Column(name = "price")
    Double price;

    @OneToMany
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    List<Photos> photos;

    @Column(name = "processed")
    Boolean processed;

    public CarDto toDto() {
        CarDto dto = new CarDto();
        dto.setId(id);
        dto.setUrl(url);
        dto.setBrand(make);
        dto.setModel(model);
        dto.setExcerpt(excerpt);
        dto.setModification(trimLevel);
        dto.setYear(year);
        dto.setColor(color);
        dto.setMileageKm(mileageKm);
        dto.setCondition(condition);
        dto.setEngineType(engineType);
        dto.setFuelType(fuelType);
        dto.setPowerKw(powerKw);
        dto.setEngineFullText(engineFullText);
        dto.setVolume(displacementCc);
        dto.setTransmission(gearboxType);
        dto.setCarClass(carClass);
        dto.setBodyType(bodyType);
        dto.setDoors(doors);
        dto.setSeats(seats);
        dto.setCurbWeightKg(curbWeightKg);
        dto.setWheelbaseMm(wheelbaseMm);
        dto.setTireSizeFront(tireSizeFront);
        dto.setTireSizeRear(tireSizeRear);
        dto.setPrice(price);
        dto.setPhotoIds(photos
                .stream()
                .map(Photos::getId)
                .toList());
        return dto;
    }
}
