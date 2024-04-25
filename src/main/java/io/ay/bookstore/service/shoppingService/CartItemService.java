package io.ay.bookstore.service.shoppingService;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.dto.shopping.CartItemDto;
import io.ay.bookstore.model.entity.shopping.CartItem;
import io.ay.bookstore.model.entity.user.User;
import io.ay.bookstore.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static io.ay.bookstore.utility.CustomMapper.map;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public ApiResponse create(CartItemDto dto, SecurityContext securityContext) {
        BigDecimal price = dto.getBook().getPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
        dto.setPrice(price);
        CartItem cartItem = map(dto);
        cartItem.setUser((User) securityContext.getAuthentication().getPrincipal());
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        CartItemDto savedDto = map(savedCartItem);
        return new ApiResponse(true, "success", savedDto, null);
    }

    public ApiResponse update(CartItemDto dto, SecurityContext securityContext) {
        User user = (User) securityContext.getAuthentication().getPrincipal();
        CartItem foundCartItem = cartItemRepository.findByBookAndUser(dto.getBook(), user)
                .orElseGet(() -> (CartItem) create(dto, securityContext).getResponseData());
        foundCartItem.setQuantity(dto.getQuantity());
        BigDecimal price = dto.getBook().getPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
        foundCartItem.setPrice(price);
        CartItem updatedCartItem = cartItemRepository.save(foundCartItem);
        CartItemDto updatedDto = map(updatedCartItem);
        return new ApiResponse(true, "success", updatedDto, null);
    }

    public ApiResponse remove(CartItemDto dto, SecurityContext securityContext) {
        User user = (User) securityContext.getAuthentication().getPrincipal();
        CartItem foundCartItem = cartItemRepository.findByBookAndUser(dto.getBook(), user)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        cartItemRepository.delete(foundCartItem);
        return new ApiResponse(true, "success", null, null);
    }
}
