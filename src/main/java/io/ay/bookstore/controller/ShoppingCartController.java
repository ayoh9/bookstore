package io.ay.bookstore.controller;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.entity.shopping.CartItem;
import io.ay.bookstore.service.shoppingService.OrderService;
import io.ay.bookstore.service.shoppingService.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import static io.ay.bookstore.utility.CustomLogger.logRequest;
import static io.ay.bookstore.utility.CustomLogger.logResponse;

@RestController
@RequestMapping("/api/${apiVersion}/cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> addItem(@RequestBody CartItem item, SecurityContext securityContext) {
        logRequest("ShoppingCartController.addItem", item.toString());
        ApiResponse response = shoppingCartService.addCartItem(item, securityContext);
        logResponse("ShoppingCartController.addItem", response.toString());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> removeItem(@RequestBody CartItem item, SecurityContext securityContext) {
        logRequest("ShoppingCartController.removeItem", item.toString());
        ApiResponse response = shoppingCartService.removeCartItem(item, securityContext);
        logResponse("ShoppingCartController.removeItem", response.toString());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> viewCart(SecurityContext securityContext) {
        logRequest("ShoppingCartController.viewCart", "");
        ApiResponse response = shoppingCartService.getShoppingCart(securityContext);
        logResponse("ShoppingCartController.viewCart", response.toString());
        return ResponseEntity.ok().body(response);
    }
}
