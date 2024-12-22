package ru.kafpin.lr6_bzz.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClient {

    private String name;
    private String surName;
    private String patronym;
    private String phone;
    private String password;

    @Override
    public String toString() {
        return getSurName() + ' ' + getName() + ' ' + getPatronym();
    }
}
