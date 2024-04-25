package io.ay.bookstore.utility;

import io.ay.bookstore.model.dto.BookDto;
import io.ay.bookstore.model.dto.shopping.CartItemDto;
import io.ay.bookstore.model.dto.shopping.ShoppingCartDto;
import io.ay.bookstore.model.entity.book.Book;
import io.ay.bookstore.model.entity.shopping.CartItem;
import io.ay.bookstore.model.entity.shopping.ShoppingCart;

public class CustomMapper {

    public static BookDto map(Book book) {
        BookDto dto = new BookDto();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setISBNCode(book.getISBNCode());
        dto.setGenre(book.getGenre().getGenreName());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setPrice(book.getPrice());
        return dto;
    }

    public static CartItem map(CartItemDto dto) {
        CartItem item = new CartItem();
        item.setBook(dto.getBook());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());
        return item;
    }

    public static CartItemDto map(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setBook(cartItem.getBook());
        dto.setQuantity(cartItem.getQuantity());
        dto.setPrice(cartItem.getPrice());
        return dto;
    }

    public static ShoppingCartDto map(ShoppingCart cart) {
        ShoppingCartDto dto = new ShoppingCartDto();
        if (!cart.getCartItems().isEmpty())
            for (CartItem item : cart.getCartItems()) {
                CartItemDto itemDto = map(item);
                dto.getCartItems().add(itemDto);
            }
        dto.setAmount(cart.getCartAmount());
        return dto;
    }

    public static ShoppingCart map(ShoppingCartDto dto) {
        ShoppingCart cart = new ShoppingCart();
        if (!dto.getCartItems().isEmpty())
            for (CartItemDto item : dto.getCartItems()) {
                CartItem cartItem = (CartItem) map(item);
                cart.getCartItems().add(cartItem);
            }
        cart.setCartAmount(dto.getAmount());
        return cart;
    }
}
