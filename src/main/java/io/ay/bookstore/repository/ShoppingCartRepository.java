package io.ay.bookstore.repository;

import io.ay.bookstore.model.entity.shopping.ShoppingCart;
import io.ay.bookstore.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.user = :user")
    Optional<ShoppingCart> findByUser(@Param("user") User user);

    @Query("SELECT sc FROM ShoppingCart sc WHERE sc.user = :user AND sc.id = :id")
    Optional<ShoppingCart> findByIdAndUser(@Param("id") long cartId, @Param("user")  User user);
}
