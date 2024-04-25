package io.ay.bookstore.service.shoppingService;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.dto.apiResponse.Pagination;
import io.ay.bookstore.model.dto.shopping.ShoppingCartDto;
import io.ay.bookstore.model.entity.shopping.Order;
import io.ay.bookstore.model.entity.shopping.OrderStatus;
import io.ay.bookstore.model.entity.shopping.ShoppingCart;
import io.ay.bookstore.model.entity.user.User;
import io.ay.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.ay.bookstore.utility.CustomMapper.map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(ShoppingCartDto shoppingCartDto, User user) {
        ShoppingCart shoppingCart = map(shoppingCartDto);
        Order order = new Order();
        order.setOrderStatus(OrderStatus.INITIALIZED);
        order.setCartItems(shoppingCart.getCartItems());
        order.setAmount(shoppingCart.getCartAmount());
        order.setUser(user);

        String transactionRef = UUID.randomUUID().toString().replaceAll("-", "");
        order.setTransactionReference(transactionRef);
        return orderRepository.save(order);
    }

    public Order findByUserAndTransactionRef(User user, String transactionReferences) {
        return orderRepository.findByTransactionReference(user, transactionReferences)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public Order update(Order order, User user) {
        Order foundOrder = findByUserAndTransactionRef(user, order.getTransactionReference());
        foundOrder.setPaymentReference(order.getPaymentReference());
        foundOrder.setPaymentMethod(order.getPaymentMethod());
        foundOrder.setOrderStatus(order.getOrderStatus());
        orderRepository.save(foundOrder);
        return foundOrder;
    }

    public ApiResponse readAll(int pageSize, int pageNumber, SecurityContext securityContext) {
        User user = (User) securityContext.getAuthentication().getPrincipal();

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Order> foundOrders = orderRepository.findByUser(user, pageable);
        Pagination pagination = new Pagination(foundOrders.getSize(), foundOrders.getNumber(), foundOrders.getTotalPages());
        return new ApiResponse(true, "success", foundOrders, pagination);
    }
}
