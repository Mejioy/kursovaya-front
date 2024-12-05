package ru.kafpin.lr6_bzz.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", patronym='" + patronym + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", appartment=" + appartment;
    }

    //    @Override
//    public String toString() {
//        return getSurName() + ' ' + getName() + ' ' + getPatronym();
//    }
    public void setNullAppartment(){
        this.appartment = null;
    }
}
