package ru.dhabits.spring_boot_example.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dhabits.spring_boot_example.Service.UserService;
import ru.dhabits.spring_boot_example.model.request.CreateUserRequest;
import ru.dhabits.spring_boot_example.model.response.UserResponse;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    //Получаем весь список пользователей
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    //Получаем пользователя по id
    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public UserResponse findById(@PathVariable Integer userId) {
        return userService.findById(userId);
    }

    //Создаем пользователя
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserResponse create(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    //Обновляем пользователя по id
    @PatchMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserResponse update(@PathVariable Integer userId, @RequestBody CreateUserRequest request) {
        return userService.update(userId, request);
    }

    //Удаляем пользователя по id
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer userId) {
        userService.delete(userId);
    }
}
