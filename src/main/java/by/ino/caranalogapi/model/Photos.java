package by.ino.caranalogapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Photos {
    @Id
    Long id;

    @Column(name = "car_id")
    Long carId;

    @Column(name = "path")
    String path;
}
