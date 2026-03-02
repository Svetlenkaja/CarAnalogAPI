package by.ino.caranalogapi.repository;

import by.ino.caranalogapi.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long>, JpaSpecificationExecutor<Cars> {
}
