package io.ay.bookstore.service.shoppingService;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.dto.shopping.ShoppingCartDto;
import io.ay.bookstore.model.entity.shopping.CartItem;
import io.ay.bookstore.model.entity.shopping.ShoppingCart;
import io.ay.bookstore.model.entity.user.User;
import io.ay.bookstore.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static io.ay.bookstore.utility.CustomMapper.map;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemService cartItemService;

    public ApiResponse addCartItem(CartItem cartItem, SecurityContext securityContext) {
        User user = (User) securityContext.getAuthentication().getPrincipal();
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findByUser(user);
        ShoppingCart sc;
        if (shoppingCart.isEmpty()) {
            sc = new ShoppingCart();
            sc.getCartItems().add(cartItem);
            sc.setUser(user);
            sc.setCartAmount(cartItem.getPrice());
        } else {
            sc = shoppingCart.get();
            sc.getCartItems().add(cartItem);
            sc.setCartAmount(sc.getCartAmount().add(cartItem.getPrice()));
        }
        ShoppingCart cart = shoppingCartRepository.save(sc);
        ShoppingCartDto dto = map(cart);
        return new ApiResponse(true, "success", dto, null);
    }

    public ApiResponse removeCartItem(CartItem cartItem, SecurityContext securityContext) {
        User user = (User) securityContext.getAuthentication().getPrincipal();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Shopping Cart not found"));
        shoppingCart.getCartItems().remove(cartItem);
        shoppingCart.setCartAmount(shoppingCart.getCartAmount().subtract(cartItem.getPrice()));
        ShoppingCart cart = shoppingCartRepository.save(shoppingCart);
        ShoppingCartDto dto = map(cart);
        return new ApiResponse(true, "success", dto, null);
    }

    public void emptyCart(User user) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Could not find shopping cart"));
        shoppingCart.getCartItems().clear();
        shoppingCart.setCartAmount(BigDecimal.ZERO);
        shoppingCartRepository.save(shoppingCart);
        //return new ApiResponse(true, "success", shoppingCart, null);
    }

    public ApiResponse getShoppingCart(SecurityContext securityContext) {
        User user = (User) securityContext.getAuthentication().getPrincipal();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Could not find shopping cart"));
        return new ApiResponse(true, "success", shoppingCart, null);
    }
}
