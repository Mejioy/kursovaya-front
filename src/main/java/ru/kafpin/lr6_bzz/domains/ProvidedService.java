package ru.kafpin.lr6_bzz.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvidedService {

    private Long id;
    private Service service;
    private Employer employer;
    private Automobile automobile;
    private LocalDate dateOfProvide;

    public java.sql.Date getSqlDate(){
        return java.sql.Date.valueOf(this.getDateOfProvide()) ;
    }

    public LocalDate getLocalDate(java.sql.Date databaseValue) {
        return (databaseValue == null) ? null
                : databaseValue.toLocalDate();
    }
}
