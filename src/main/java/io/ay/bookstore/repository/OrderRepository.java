package io.ay.bookstore.repository;

import io.ay.bookstore.model.entity.shopping.Order;
import io.ay.bookstore.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT od FROM Order od WHERE od.user = :user")
    Page<Order> findByUser(@Param("user")User user, Pageable pageable);

    @Query("SELECT od FROM Order od WHERE od.user = :user AND od.transactionReference = :txnRef")
    Optional<Order> findByTransactionReference(@Param("user")User user, @Param("txnRef") String transactionReference);
}
