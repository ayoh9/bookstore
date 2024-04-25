package io.ay.bookstore.service.bookService;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.dto.BookDto;
import org.springframework.data.domain.Pageable;

public interface BookService {

    ApiResponse create(BookDto bookDto);
    ApiResponse findAll(String title, String author, int publicationYear, String genre, Pageable pageable);
}
