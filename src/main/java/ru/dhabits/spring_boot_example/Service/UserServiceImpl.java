package ru.dhabits.spring_boot_example.Service;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dhabits.spring_boot_example.domain.Address;
import ru.dhabits.spring_boot_example.domain.User;
import ru.dhabits.spring_boot_example.model.request.CreateAddressRequest;
import ru.dhabits.spring_boot_example.model.request.CreateUserRequest;
import ru.dhabits.spring_boot_example.model.response.AddressResponse;
import ru.dhabits.spring_boot_example.model.response.UserResponse;
import ru.dhabits.spring_boot_example.repository.UserRepository;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static UserRepository userRepository;

    //Получаем весь список пользователей
    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    //Получаем пользователя по id
    @NotNull
    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(@NotNull Integer userId) {
        return userRepository.findById(userId)
                .map(this::buildUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
    }

    //Создаем пользователя
    @NotNull
    @Override
    @Transactional
    public UserResponse createUser(@NotNull CreateUserRequest request) {
        User user = buildUserRequest(request);
        return buildUserResponse(userRepository.save(user));
    }

    //Обновляем пользователя по id
    @NotNull
    @Override
    @Transactional
    public UserResponse update(@NotNull Integer userId, @NotNull CreateUserRequest request) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
        userUpdate(user, request);
        return buildUserResponse(userRepository.save(user));
    }

    //Удаляем пользователя по id
    @Override
    @Transactional
    public void delete(@NotNull Integer userId) {
        userRepository.deleteById(userId);
    }

    @NotNull
    private UserResponse buildUserResponse(@NotNull User user) {
        return new UserResponse()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setAge(user.getAge())
                .setFirstName(user.getFirstName())
                .setMiddleName(user.getMiddleName())
                .setLasName(user.getLasName())
                .setAddress(new AddressResponse()
                        .setCity(user.getAddress().getCity())
                        .setBuilding(user.getAddress().getBuilding())
                        .setStreet(user.getAddress().getStreet()));
    }

    @NotNull
    private User buildUserRequest(@NotNull CreateUserRequest request) {
        return new User()
                .setLogin(request.getLogin())
                .setAge(request.getAge())
                .setFirstName(request.getFirstName())
                .setMiddleName(request.getMiddleName())
                .setLasName(request.getLasName())
                .setAddress(new Address()
                        .setCity(request.getAddress().getCity())
                        .setBuilding(request.getAddress().getBuilding())
                        .setStreet(request.getAddress().getStreet()));
    }

    private void userUpdate(@NotNull User user, @NotNull CreateUserRequest request) {
        ofNullable(request.getLogin()).map(user::setLogin);
        ofNullable(request.getFirstName()).map(user::setFirstName);
        ofNullable(request.getMiddleName()).map(user::setMiddleName);
        ofNullable(request.getLasName()).map(user::setLasName);
        ofNullable(request.getAge()).map(user::setAge);

        CreateAddressRequest addressRequest = request.getAddress();
        if (addressRequest != null) {
            ofNullable(addressRequest.getBuilding()).map(user.getAddress()::setBuilding);
            ofNullable(addressRequest.getStreet()).map(user.getAddress()::setStreet);
            ofNullable(addressRequest.getCity()).map(user.getAddress()::setCity);
        }
    }

}
