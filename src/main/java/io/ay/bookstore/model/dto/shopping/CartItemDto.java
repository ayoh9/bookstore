package io.ay.bookstore.model.dto.shopping;

import com.google.gson.Gson;
import io.ay.bookstore.model.entity.book.Book;
import io.ay.bookstore.utility.annotation.NotZero;
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
public class CartItemDto {

    @NotNull(message = "Book must be provided")
    private Book book;

    @NotZero(message = "Quantity must be provided")
    private int quantity;
    private BigDecimal price;

    @Override
    public String toString() {
        try {
            return new Gson().toJson(this);
        } catch (Exception ex) {
            return super.toString();
        }
    }
}
