package io.ay.bookstore.service.shoppingService;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.dto.shopping.CheckoutDto;
import io.ay.bookstore.model.dto.shopping.ShoppingCartDto;
import io.ay.bookstore.model.entity.shopping.Order;
import io.ay.bookstore.model.entity.shopping.OrderStatus;
import io.ay.bookstore.model.entity.shopping.PaymentMethod;
import io.ay.bookstore.model.entity.shopping.ShoppingCart;
import io.ay.bookstore.model.entity.user.User;
import io.ay.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.ay.bookstore.utility.CustomMapper.map;

@Service
@RequiredArgsConstructor
public class CheckoutService {


    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    public ApiResponse initiateCheckout(SecurityContext securityContext) {
        User user = (User) securityContext.getAuthentication().getPrincipal();
        //if (user != shoppingCart.getUser()) throw new IllegalArgumentException("User action not permitted");

        ShoppingCart shoppingCart = (ShoppingCart) shoppingCartService.getShoppingCart(securityContext).getResponseData();
        ShoppingCartDto shoppingCartDto = map(shoppingCart);
        Order order = orderService.create(shoppingCartDto, user);
        CheckoutDto checkoutDto = new CheckoutDto();
        checkoutDto.setTransactionReference(order.getTransactionReference());
        checkoutDto.setAmount(order.getAmount());
        return new ApiResponse(true, "success", checkoutDto, null);
    }

    public ApiResponse completeCheckout(CheckoutDto checkoutDto, SecurityContext securityContext) {
        User user = (User) securityContext.getAuthentication().getPrincipal();
        Order order = orderService.findByUserAndTransactionRef(user, checkoutDto.getTransactionReference());
        order.setPaymentReference(checkoutDto.getPaymentReference());
        order.setPaymentMethod(PaymentMethod.valueOf(checkoutDto.getPaymentMethod()));
        order.setOrderStatus(OrderStatus.COMPLETED);
        Order updatedOrder = orderService.update(order, user);
        shoppingCartService.emptyCart(user);
        return new ApiResponse(true, "success", updatedOrder.getTransactionReference(), null);
    }
}
