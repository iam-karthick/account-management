package com.example.account_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Entity
@Data
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Column(nullable = false)
    private String password;

    // Implement the UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You may need to implement this based on your roles/permissions
        return null; // Return the appropriate authorities
    }

    @Override
    public String getUsername() {
        return this.email; // Use email as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement based on your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement based on your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement based on your logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement based on your logic
    }
}
