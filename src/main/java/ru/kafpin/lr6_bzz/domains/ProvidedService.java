package ru.kafpin.lr6_bzz.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class ProvidedService {

    private long id;
    private long service_id;
    private long employer_id;
    private long automobile_id;
    private LocalDate dateOfProvide;

    public ProvidedService(long id, long service_id, long employer_id, long automobile_id, LocalDate dateOfProvide) {
        this.id = id;
        this.service_id = service_id;
        this.employer_id = employer_id;
        this.automobile_id = automobile_id;
        this.dateOfProvide = dateOfProvide;
    }

    public java.sql.Date getSqlDate(){
        return java.sql.Date.valueOf(this.getDateOfProvide()) ;
    }

    public LocalDate getLocalDate(java.sql.Date databaseValue) {
        return (databaseValue == null) ? null
                : databaseValue.toLocalDate();
    }
}
