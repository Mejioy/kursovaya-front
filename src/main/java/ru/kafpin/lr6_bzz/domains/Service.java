package ru.kafpin.lr6_bzz.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    private Long id;
    private String name;
    private int price;
    private String description;
    private boolean status;

//    public Service(long id, String name, int price) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }

    public void setNullDescription(){
        this.description = null;
    }

    @Override
    public String toString() {
        return getName();
    }
}
