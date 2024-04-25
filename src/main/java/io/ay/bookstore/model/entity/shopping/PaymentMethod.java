package io.ay.bookstore.model.entity.shopping;

import lombok.Getter;

@Getter
public enum PaymentMethod {

    WEB("WEB"),
    USSD("USSD"),
    TRANSFER("TRANSFER");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }
}
