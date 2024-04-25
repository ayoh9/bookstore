package io.ay.bookstore.controller;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.service.shoppingService.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.ay.bookstore.utility.CustomLogger.logRequest;
import static io.ay.bookstore.utility.CustomLogger.logResponse;

@RestController
@RequestMapping("/api/${apiVersion}/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/history")
    public ResponseEntity<ApiResponse> purchaseHistory(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                       @RequestParam(defaultValue = "10", required = false) int pageSize,
                                                       SecurityContext securityContext) {
        logRequest("OrderController.purchaseHistory", "");
        ApiResponse response = orderService.readAll(pageSize, pageNumber, securityContext);
        logResponse(" OrderController.purchaseHistory", response.toString());
        return ResponseEntity.ok().body(response);
    }
}
