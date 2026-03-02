package by.ino.caranalogapi.model;


import java.util.List;

public record PaginationPage(List<CarDto> items, int page, int pageSize, int totalCount, int totalPage) {
}
