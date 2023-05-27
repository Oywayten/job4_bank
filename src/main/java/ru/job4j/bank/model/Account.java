package ru.job4j.bank.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Oywayten 26.05.2023.
 */
@Getter
@Setter
public class Account extends Id {

    private String requisite;

    private double balance;

    private User user;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }


}