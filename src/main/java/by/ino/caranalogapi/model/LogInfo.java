package by.ino.caranalogapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "log_info")
@NoArgsConstructor
public class LogInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "params")
    String params;
    @Column(name = "result")
    String result;

    @Column(name = "success")
    Integer success;
    @Column(name = "created_at")
    LocalDateTime createdAt;

    public LogInfo(String params, String result, Integer success) {
        this.params = params;
        this.success = success;
        this.result = result;
        this.createdAt = LocalDateTime.now();
    }
}
