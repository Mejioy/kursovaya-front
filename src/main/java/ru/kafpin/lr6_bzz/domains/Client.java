package ru.kafpin.lr6_bzz.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Client  {

    private long id;
    private String name;
    private String surName;
    private String patronym;
    private String phone;
    private List<Automobile> automobiles;

    public Client(long id, String surName, String name, String patronym, String phone) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.patronym = patronym;
        this.phone = phone;
    }
    @Override
    public String toString() {
        return getSurName() + ' ' + getName() + ' ' + getPatronym();
    }
}
