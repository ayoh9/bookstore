package io.ay.bookstore.model.dto;

import com.google.gson.Gson;
import io.ay.bookstore.model.entity.book.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BookDto {

    @NotBlank(message = "Title must be provided")
    @Pattern(regexp = "[a-zA-Z0-9]", message = "Must contain only alphanumeric characters")
    private String title;

    @NotBlank(message = "Author must be provided")
    private String Author;

    @NotBlank(message = "Genre must be provided")
    @Pattern(regexp = "[a-zA-Z]", message = "Must contain only alphabets")
    private String genre;

    @NotBlank(message = "ISBN Code must be provided")
    @Pattern(regexp = "[0-9-]", message = "Must contain only number and dash")
    private String ISBNCode;

    @NotNull(message = "Publication Year must be provided")
    private int publicationYear;

    @NotNull(message = "Price must be provided")
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
