package ru.job4j.bank.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Oywayten 26.05.2023.
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Id {

    @EqualsAndHashCode.Include
    protected int id;

    public Id() {}

}