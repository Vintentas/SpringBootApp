package ru.dhabits.spring_boot_example.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Accessors(chain = true)
public class AddressResponse {
    private String street;
    private String city;
    private String building;
}
