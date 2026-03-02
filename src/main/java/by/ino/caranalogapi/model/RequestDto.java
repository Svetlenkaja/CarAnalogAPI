package by.ino.caranalogapi.model;

import jakarta.validation.constraints.Max;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Data
public class RequestDto {
    @NotBlank(message = "Укажите марку автомобиля")
    String brand;
    @NotBlank(message = "Укажите модель автомобиля")
    String model;
    String modification;
    List<String> engineType;
    Integer yearFrom;
    Integer yearTo;
    Integer volumeFrom;
    Integer volumeTo;
    Integer mileageFrom;
    Integer mileageTo;
    List<String> transmission;
    List<String> bodyType;
    String country;
    Integer page = 1;
    @Max(50)
    Integer pageSize = 20;
}
