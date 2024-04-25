package io.ay.bookstore.controller;

import io.ay.bookstore.model.dto.BookDto;
import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.service.bookService.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static io.ay.bookstore.utility.CustomLogger.*;

@RestController
@RequestMapping("/api/${apiVersion}/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @NotNull(message = "Valid request body is required")
                                                  @RequestBody BookDto bookDto, @ApiIgnore Errors errors) {
        logRequest("BookController.create", bookDto.toString());
        logError(errors);
        ApiResponse response = bookService.create(bookDto);
        logResponse("BookController.create", response.toString());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                               @RequestParam(defaultValue = "10", required = false) int pageSize,
                                               @RequestParam(required = false) String title,
                                               @RequestParam(required = false) String author,
                                               @RequestParam(name = "year", required = false) int publicationYear,
                                               @RequestParam(required = false) String genre) {
        logRequest("BookController.findAll", "Title: " + title + ", Author: " + author +
                ", Year: " + publicationYear + ", Genre: " + genre);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        ApiResponse response = bookService.findAll(title, author, publicationYear, genre, pageable);
        logResponse("BookController.create", response.toString());
        return ResponseEntity.ok().body(response);
    }
}
