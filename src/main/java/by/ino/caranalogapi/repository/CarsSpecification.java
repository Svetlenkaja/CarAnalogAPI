package by.ino.caranalogapi.repository;

import by.ino.caranalogapi.model.Cars;
import by.ino.caranalogapi.model.RequestDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class CarsSpecification {

    private CarsSpecification() {}

    public static Specification<Cars> fromRequest(RequestDto request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isTrue(root.get("processed")));

            // brand -> make
            if (request.getBrand() != null && !request.getBrand().isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("make")), request.getBrand().toLowerCase().trim()));
            }

            if (request.getModel() != null && !request.getModel().isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("model")), request.getModel().toLowerCase().trim()));
            }

            // modification -> trimLevel
            if (request.getModification() != null && !request.getModification().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("trimLevel")),
                        "%" + request.getModification().toLowerCase().trim() + "%"));
            }

            if (request.getEngineType() != null && !request.getEngineType().isEmpty()) {
                predicates.add(root.get("engineType").in(request.getEngineType()));
            }

            if (request.getYearFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("year"), request.getYearFrom()));
            }
            if (request.getYearTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("year"), request.getYearTo()));
            }

            // volumeFrom, volumeTo -> displacementCc
            if (request.getVolumeFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("displacementCc"), request.getVolumeFrom()));
            }
            if (request.getVolumeTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("displacementCc"), request.getVolumeTo()));
            }

            if (request.getMileageFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("mileageKm"), request.getMileageFrom()));
            }
            if (request.getMileageTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("mileageKm"), request.getMileageTo()));
            }

            // transmission (List) -> gearboxType
            if (request.getTransmission() != null && !request.getTransmission().isEmpty()) {
                predicates.add(root.get("gearboxType").in(request.getTransmission()));
            }

            if (request.getBodyType() != null && !request.getBodyType().isEmpty()) {
                predicates.add(root.get("bodyType").in(request.getBodyType()));
            }

            // country - в Cars нет поля country
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
