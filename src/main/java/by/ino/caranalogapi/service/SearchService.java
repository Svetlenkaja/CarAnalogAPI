package by.ino.caranalogapi.service;


import by.ino.caranalogapi.aspect.Logging;
import by.ino.caranalogapi.model.PaginationPage;
import by.ino.caranalogapi.model.RequestDto;

public interface SearchService {
    @Logging
    PaginationPage search(RequestDto request);
}
