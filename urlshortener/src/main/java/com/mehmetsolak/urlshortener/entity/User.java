package com.mehmetsolak.urlshortener.entity;

import com.mehmetsolak.urlshortener.dto.response.UserDtoResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "users")
public final class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @Email
    private String email;

    @CreationTimestamp
    private Date creationDate;

    @OneToMany(mappedBy = "user")
    private List<Url> urls;

    public User() { }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public UserDtoResponse toDto() {
        return new UserDtoResponse(
                username,
                email,
                creationDate,
                Optional.ofNullable(urls).orElse(Collections.emptyList())
                        .stream()
                        .map(Url::toDto)
                        .collect(Collectors.toList())
        );
    }
}
