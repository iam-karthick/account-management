package com.example.account_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    // @NotBlank(message = "Password is mandatory")
    // @Size(min = 8, message = "Password must be at least 8 characters long")
    // @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must contain only alphabets and numbers")
    // private String password;
    @Column(nullable = false)
    // @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password must contain only alphabets and numbers")
    private String password;
}
