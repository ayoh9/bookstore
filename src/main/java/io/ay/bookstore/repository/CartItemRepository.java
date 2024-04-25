package io.ay.bookstore.repository;

import io.ay.bookstore.model.entity.book.Book;
import io.ay.bookstore.model.entity.shopping.CartItem;
import io.ay.bookstore.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT cItem FROM CartItem cItem WHERE cItem.book = :book AND cItem.user = :user")
    Optional<CartItem> findByBookAndUser(@Param("book") Book book, @Param("user") User user);
}
