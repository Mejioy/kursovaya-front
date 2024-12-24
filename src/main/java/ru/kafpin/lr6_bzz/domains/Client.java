package ru.kafpin.lr6_bzz.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client  {

    private Long id;
    private String name;
    private String surName;
    private String patronym;
    private String phone;

    @Override
    public String toString() {
        return getSurName() + ' ' + getName() + ' ' + getPatronym() + ' ' + getPhone();
    }

    public String getFio(){
        return getSurName() + ' ' + getName() + ' ' + getPatronym();
    }
}
