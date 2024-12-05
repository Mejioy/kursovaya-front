package ru.kafpin.lr6_bzz.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Automobile {

    private long id;
    private String mark;
    private String model;
    private String gosnumber;
    private long client_id;

    private List<ProvidedService> providedServices;

    public Automobile(long id, String mark, String model, String gosnumber, long client_id) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.gosnumber = gosnumber;
        this.client_id = client_id;
    }
    @Override
    public String toString() {
        return getMark() + ' ' + getModel() + ' ' + getGosnumber();
    }
}
