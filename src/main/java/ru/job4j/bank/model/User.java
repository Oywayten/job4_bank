package ru.job4j.bank.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Oywayten 26.05.2023.
 */
@Getter
@Setter
public class User extends Id {
    private String username;
    private String password;
    private String passport;
    private final List<Account> accounts = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}