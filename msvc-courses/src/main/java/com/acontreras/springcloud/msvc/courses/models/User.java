package com.acontreras.springcloud.msvc.courses.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class User {
    private Long id;

    private String name;

    private String email;

    private String password;
}
