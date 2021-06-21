package ru.dhabits.spring_boot_example.Service;

import com.sun.istack.NotNull;
import ru.dhabits.spring_boot_example.model.request.CreateUserRequest;
import ru.dhabits.spring_boot_example.model.response.UserResponse;

import java.util.List;

public interface UserService {

    @NotNull
    List<UserResponse> findAll();

    @NotNull
    UserResponse findById(@NotNull Integer userId);

    @NotNull
    UserResponse createUser(@NotNull CreateUserRequest request);

    @NotNull
    UserResponse update(@NotNull Integer userId, @NotNull CreateUserRequest request);

    void delete(@NotNull Integer userId);
}


