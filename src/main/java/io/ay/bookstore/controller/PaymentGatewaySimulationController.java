package io.ay.bookstore.controller;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.dto.shopping.CheckoutDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/pg-simulator")
public class PaymentGatewaySimulationController {

    @Value("${activePayment}")
    private String paymentMethod;

    public ResponseEntity<ApiResponse> simulate(@RequestBody CheckoutDto dto, SecurityContext securityContext) {
        dto.setPaymentMethod(paymentMethod);
        dto.setPaymentReference(UUID.randomUUID().toString());
        ApiResponse response = new ApiResponse(true, "success", dto, null);
        return ResponseEntity.ok().body(response);
    }
}
