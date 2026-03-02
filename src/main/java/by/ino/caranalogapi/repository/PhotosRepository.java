package by.ino.caranalogapi.repository;

import by.ino.caranalogapi.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, Long> {
}
