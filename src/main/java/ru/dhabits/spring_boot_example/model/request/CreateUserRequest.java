package ru.dhabits.spring_boot_example.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserRequest {
    private Integer id;
    private String login;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private CreateAddressRequest address;
}
