package by.ino.caranalogapi.service;

import by.ino.caranalogapi.aspect.Logging;
import by.ino.caranalogapi.exception.ServiceException;
import by.ino.caranalogapi.model.CarDto;
import by.ino.caranalogapi.model.Cars;
import by.ino.caranalogapi.model.PaginationPage;
import by.ino.caranalogapi.model.RequestDto;
import by.ino.caranalogapi.repository.CarsRepository;
import by.ino.caranalogapi.repository.CarsSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class SearchServiceImpl implements SearchService {

    final CarsRepository carsRepository;

    static final Integer PAGE_SIZE = 20;
    static final Integer PAGE_NUMBER = 1;

    @Override
    @Logging
    public PaginationPage search(RequestDto request) {
        try {
            int page = request.getPage() != null ? request.getPage() : PAGE_NUMBER;
            int pageSize = request.getPageSize() != null ? request.getPageSize() : PAGE_SIZE;

            var spec = CarsSpecification.fromRequest(request);
            var pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("id").ascending());
            var result = carsRepository.findAll(spec, pageRequest);

            int totalCount = (int) result.getTotalElements();
            int totalPage = (totalCount + pageSize - 1) / pageSize;
            //throw new ServiceException("test");
            List<CarDto> items = result
                    .getContent()
                    .stream()
                    .map(Cars::toDto)
                    .toList();

            return new PaginationPage(items, page, pageSize, totalCount, totalPage);
        } catch (RuntimeException e) {
            log.error("Error  {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}
