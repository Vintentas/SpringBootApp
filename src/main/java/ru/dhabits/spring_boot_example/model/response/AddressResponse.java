package ru.dhabits.spring_boot_example.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddressResponse {
    private String street;
    private String city;
    private String building;
}
