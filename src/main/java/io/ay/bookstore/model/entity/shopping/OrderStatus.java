package io.ay.bookstore.model.entity.shopping;

import lombok.Getter;

@Getter
public enum OrderStatus {

    INITIALIZED("INITIALIZED"),
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
