package io.ay.bookstore.controller;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.dto.shopping.CheckoutDto;
import io.ay.bookstore.service.shoppingService.CheckoutService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import static io.ay.bookstore.utility.CustomLogger.*;

@RestController
@RequestMapping("/api/${apiVersion}/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/init")
    public ResponseEntity<ApiResponse> initiateCheckout(SecurityContext securityContext) {
        logRequest("CheckoutController.initiateCheckout", "");
        ApiResponse response = checkoutService.initiateCheckout(securityContext);
        logResponse("CheckoutController.initiateCheckout", response.toString());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/complete")
    public ResponseEntity<ApiResponse> completeCheckout(@Valid @NotNull(message = "Valid request body is required")
                                                           @RequestBody CheckoutDto dto, SecurityContext securityContext,
                                                       @ApiIgnore Errors errors) {
        logRequest("CheckoutController.completeCheckout", dto.toString());
        logError(errors);
        ApiResponse response = checkoutService.completeCheckout(dto, securityContext);
        logResponse("CheckoutController.completeCheckout", response.toString());
        return ResponseEntity.ok().body(response);
    }
}
