package io.ay.bookstore.controller;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.dto.shopping.CartItemDto;
import io.ay.bookstore.service.shoppingService.CartItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static io.ay.bookstore.utility.CustomLogger.*;

@RestController
@RequestMapping("/api/${apiVersion}/items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @NotNull(message = "Valid request body is required")
                                               @RequestBody CartItemDto cartItemDto, SecurityContext securityContext,
                                           @ApiIgnore Errors errors) {
        logRequest("CartItemController.add", cartItemDto.toString());
        logError(errors);
        ApiResponse response = cartItemService.create(cartItemDto, securityContext);
        logResponse("CartItemController.add", response.toString());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(@Valid @NotNull(message = "Valid request body is required")
                                                  @RequestBody CartItemDto cartItemDto, SecurityContext securityContext,
                                              @ApiIgnore Errors errors) {
        logRequest("CartItemController.update", cartItemDto.toString());
        logError(errors);
        ApiResponse response = cartItemService.update(cartItemDto, securityContext);
        logResponse("CartItemController.update", response.toString());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(@Valid @NotNull(message = "Valid request body is required")
                                                  @RequestBody CartItemDto cartItemDto, SecurityContext securityContext,
                                              @ApiIgnore Errors errors) {
        logRequest("CartItemController.delete", cartItemDto.toString());
        logError(errors);
        ApiResponse response = cartItemService.remove(cartItemDto, securityContext);
        logResponse("CartItemController.delete", response.toString());
        return ResponseEntity.ok().body(response);
    }
}
