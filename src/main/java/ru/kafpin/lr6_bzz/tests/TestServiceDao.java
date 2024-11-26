package ru.kafpin.lr6_bzz.tests;

import ru.kafpin.lr6_bzz.dao.ServiceDao;
import ru.kafpin.lr6_bzz.domains.Automobile;
import ru.kafpin.lr6_bzz.domains.Service;

import java.util.Collection;

public class TestServiceDao {
    public static void main(String[] args) {
        ServiceDao Dao = null;
        try {
            Dao = new ServiceDao();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        delete(Dao);
//        Collection<Service> al = selectall(Dao);
//        for (Service cl :al
//             ) {
//            System.out.println(cl);
//        }
        Service service = Dao.findById(1L);
        System.out.println(service);
    }
    static void insert(ServiceDao Dao){
        Dao.save(new Service("Валуев",10,0));
    }
    static void update(ServiceDao Dao){Dao.update(new Service(8,"gggg",2,1,"1"));}
    static void delete(ServiceDao clientDao){long j = 8; clientDao.deleteById(j);}
    static Collection<Service> selectall(ServiceDao Dao){return  Dao.findALl();}

}
