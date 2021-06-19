package ru.dhabits.spring_boot_example.model.request;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.dhabits.spring_boot_example.model.response.AddressResponse;

@Data
@Accessors(chain = true)
public class CreateUserRequest {

    private Integer id;
    private String login;
    private String firstName;
    private String middleName;
    private String lasName;
    private Integer age;
    private CreateAddressRequest address;
}
