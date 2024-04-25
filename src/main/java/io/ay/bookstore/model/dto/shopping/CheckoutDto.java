package io.ay.bookstore.model.dto.shopping;

import io.ay.bookstore.model.entity.shopping.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CheckoutDto {

    @NotBlank(message = "Transaction reference must be provided")
    private String transactionReference;

    @NotNull(message = "Transaction amount must be provided")
    private BigDecimal amount;

    @NotBlank(message = "Payment reference must be provided")
    private String paymentReference;

    @NotBlank(message = "Payment method must be provided")
    private String paymentMethod;
}
