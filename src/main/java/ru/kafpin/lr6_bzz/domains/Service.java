package ru.kafpin.lr6_bzz.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Service {

    private Long id;
    private String name;
    private int price;
    private String description;
    private boolean status;

    public void setNullDescription(){
        this.description = null;
    }

    @Override
    public String toString() {
        return getName();
    }
}
