//package ru.kafpin.lr6_bzz.tests;
//
//import ru.kafpin.lr6_bzz.dao.AutomobileDao;
//import ru.kafpin.lr6_bzz.dao.Dao;
//import ru.kafpin.lr6_bzz.domains.Automobile;
//
//import java.util.Collection;
//
//public class TestAutomobileDao {
//    public static void main(String[] args) {
//        AutomobileDao Dao = null;
//        try {
//            Dao = new AutomobileDao();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
////        delete(Dao);
////        Collection<Automobile> al = selectall(Dao);
////        for (Automobile cl :al
////             ) {
////            System.out.println(cl);
////        }
//        Automobile auto = Dao.findById(1L);
//        System.out.println(auto);
//    }
//    static void insert(AutomobileDao Dao){
//        Dao.save(new Automobile("Валуев","Георгий","P444MP456rus",1));
//    }
//    static void update(AutomobileDao Dao){Dao.update(new Automobile(23,"gggg","Георгий","P444MP456rus",1));}
//    static void delete(AutomobileDao clientDao){long j = 23; clientDao.deleteById(j);}
//    static Collection<Automobile> selectall(AutomobileDao Dao){long j = 1;return  Dao.findALlCarsOfOwner(j);}
//
//}
