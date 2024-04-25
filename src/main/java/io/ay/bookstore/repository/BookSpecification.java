package io.ay.bookstore.repository;

import io.ay.bookstore.model.entity.book.Book;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> filterBook(String title, String author, String publicationYear, String genre) {
        return (root, query, criteriaBuilder) -> {
            Predicate titlePredicate = criteriaBuilder.like(root.get("title"), likePattern(title));
            Predicate authorPredicate = criteriaBuilder.like(root.get("author"), likePattern(author));
            Predicate pubYearPredicate = criteriaBuilder.like(root.get("publicationYear"), likePattern(publicationYear));
            Predicate genrePredicate = criteriaBuilder.like(root.get("genre"), likePattern(genre));
            return criteriaBuilder.and(titlePredicate, authorPredicate, pubYearPredicate, genrePredicate);
        };
    }

    private static String likePattern(String value) {
        return value.isBlank() ? "%%" : "%" + value + "%";
    }
}
