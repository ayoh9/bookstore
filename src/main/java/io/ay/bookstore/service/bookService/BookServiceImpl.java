package io.ay.bookstore.service.bookService;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.dto.BookDto;
import io.ay.bookstore.model.dto.apiResponse.Pagination;
import io.ay.bookstore.model.entity.book.Book;
import io.ay.bookstore.model.entity.book.Genre;
import io.ay.bookstore.repository.BookRepository;
import io.ay.bookstore.repository.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.ay.bookstore.utility.CustomLogger.logRequest;
import static io.ay.bookstore.utility.CustomLogger.logResponse;
import static io.ay.bookstore.utility.CustomMapper.map;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public ApiResponse create(BookDto bookDto) {
        logRequest("CREATE BOOK", bookDto);
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setISBNCode(bookDto.getISBNCode());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setPrice(bookDto.getPrice());
        book.setGenre(Genre.valueOf(bookDto.getGenre()));

        Book savedBook = bookRepository.findByISBNCode(bookDto.getISBNCode())
                .orElseGet(() -> bookRepository.save(book));
        BookDto response = map(savedBook);
        logResponse("CREATE BOOK", response);
        return new ApiResponse(true, "success", response, null);
    }

    @Override
    public ApiResponse findAll(String title, String author, int publicationYear, String genre, Pageable pageable) {
        Specification<Book> specification = BookSpecification.filterBook(title, author,
                Integer.valueOf(publicationYear).toString(), genre);

        Page<Book> pageOfBooks = bookRepository.findAll(specification, pageable);
        Pagination pagination = new Pagination(pageOfBooks.getSize(), pageOfBooks.getNumber(), pageOfBooks.getTotalPages());
        logResponse("FIND BOOKS", pageOfBooks.getContent().toString());
        return new ApiResponse(true, "success", pageOfBooks.getContent(), pagination);
    }
}
