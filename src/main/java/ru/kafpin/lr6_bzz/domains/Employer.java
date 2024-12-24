package ru.kafpin.lr6_bzz.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employer {

    private Long id;
    private String name;
    private String surName;
    private String patronym;
    private String phone;
    private String city;
    private String street;
    private int house;
    private Integer appartment;
    private boolean status;

    @Override
    public String toString() {
        return getSurName() + ' ' + getName() + ' ' + getPatronym() + ' ' + getPhone();
    }

    public String getFio(){
        return getSurName() + ' ' + getName() + ' ' + getPatronym();
    }

    public void setNullAppartment(){
        this.appartment = null;
    }
}
