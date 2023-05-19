package com.test.test2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cart_user")
public class CartUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    public String firstName;
    public String lastName;
    private String userName;
    private String password;
    public String email;
}
