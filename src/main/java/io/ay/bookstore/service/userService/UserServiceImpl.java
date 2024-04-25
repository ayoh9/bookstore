package io.ay.bookstore.service.userService;

import com.google.gson.Gson;
import io.ay.bookstore.exception.UserAlreadyExistsException;
import io.ay.bookstore.model.dto.CreateUserDto;
import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import io.ay.bookstore.model.entity.user.User;
import io.ay.bookstore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static io.ay.bookstore.utility.CustomLogger.logInfo;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApiResponse createUser(CreateUserDto createUserDto) {
        logInfo("CREATE USER DTO ==> " + createUserDto);
        User userToCreate= userRepository.findByUsernameOrEmail(createUserDto.getUsername(), createUserDto.getEmail())
                .orElseGet(() -> {
                    createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
                    return new Gson().fromJson(new Gson().toJson(createUserDto), User.class);
                });
        logInfo("CREATED USER ==> " + userToCreate);
        if (userToCreate.getId() > 0) throw new UserAlreadyExistsException(createUserDto);

        User savedUser = userRepository.save(userToCreate);
        logInfo("SAVED USER ==> " + savedUser);
        return new ApiResponse();
    }
}
