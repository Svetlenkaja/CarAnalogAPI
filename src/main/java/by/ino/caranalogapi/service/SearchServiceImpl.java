package by.ino.caranalogapi.service;

import by.ino.caranalogapi.model.Cars;
import by.ino.caranalogapi.repository.CarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsServiceImpl implements CarsService {

    final CarsRepository carsRepository;
    @Override
    public List<Cars> getAll() {
        return carsRepository.findAll().stream().limit(10).toList();
    }
}
