package ru.dhabits.spring_boot_example.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponse {

    private Integer id;
    private String login;
    private String firstName;
    private String middleName;
    private String lasName;
    private Integer age;
    private AddressResponse address;
}
