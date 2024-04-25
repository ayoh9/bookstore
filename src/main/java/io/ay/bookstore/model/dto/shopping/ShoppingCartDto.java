package io.ay.bookstore.model.dto.shopping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ShoppingCartDto {

    private List<CartItemDto> cartItems = new ArrayList<>();
    private BigDecimal amount;
}
