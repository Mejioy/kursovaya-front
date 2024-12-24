package ru.kafpin.lr6_bzz.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Automobile {

    private Long id;
    private String mark;
    private String model;
    private String gosnumber;
    private Client client;
    private boolean status;

    @Override
    public String toString() {
        return getMark() + ' ' + getModel() + ' ' + getGosnumber();
    }
}
