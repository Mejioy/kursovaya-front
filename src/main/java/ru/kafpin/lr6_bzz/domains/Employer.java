package ru.kafpin.lr6_bzz.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Employer {

    private long id;
    private String name;
    private String surName;
    private String patronym;
    private String phone;
    private String city;
    private String street;
    private int house;
    private Integer appartment;
    private List<ProvidedService> providedServices;

    public Employer(long id, String surName, String name, String patronym, String phone,  String city, String street, int house) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.patronym = patronym;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Employer(long id,String surName, String name, String patronym, String phone,  String city, String street, int house, int appartment) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.patronym = patronym;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.house = house;
        this.appartment = appartment;
    }
    @Override
    public String toString() {
        return getSurName() + ' ' + getName() + ' ' + getPatronym();
    }
    public void setNullAppartment(){
        this.appartment = null;
    }
}
