package ru.kafpin.lr6_bzz.domains;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Service {

    private long id;
    private String name;
    private int price;
    private String description;

    public Service(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Service(long id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
    @Override
    public String toString() {
        return getName();
    }
}
