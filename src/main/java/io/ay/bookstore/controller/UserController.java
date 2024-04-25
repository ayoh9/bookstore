package io.ay.bookstore.controller;

import io.ay.bookstore.model.dto.CreateUserDto;
import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.service.userService.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import static io.ay.bookstore.utility.CustomLogger.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@NotNull(message = "Valid request body is required") @Valid
                                                  @RequestBody CreateUserDto request, @ApiIgnore Errors errors) {
        logRequest("UserController.createUser", request.toString());
        logError(errors);
        ApiResponse response = userService.createUser(request);
        logResponse("UserController.createUser", response.toString());
        return ResponseEntity.ok(response);
    }
}
