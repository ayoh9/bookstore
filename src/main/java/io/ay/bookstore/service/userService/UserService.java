package io.ay.bookstore.service.userService;

import io.ay.bookstore.model.dto.CreateUserDto;
import io.ay.bookstore.model.dto.apiResponse.ApiResponse;

public interface UserService {

    ApiResponse createUser(CreateUserDto createUserDto);
}
