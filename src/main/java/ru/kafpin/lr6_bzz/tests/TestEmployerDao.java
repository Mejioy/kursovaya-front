//package ru.kafpin.lr6_bzz.tests;
//
//import ru.kafpin.lr6_bzz.dao.EmployerDao;
//import ru.kafpin.lr6_bzz.domains.Client;
//import ru.kafpin.lr6_bzz.domains.Employer;
//
//import java.util.Collection;
//
//public class TestEmployerDao {
//    public static void main(String[] args) {
//        EmployerDao Dao = null;
//        try {
//            Dao = new EmployerDao();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        //delete(Dao);
////        Collection<Employer> al = selectall(Dao);
////        for (Employer cl :al
////             ) {
////            System.out.println(cl);
////        }
//        Employer employer = Dao.findById(1L);
//        System.out.println(employer);
//    }
//    static void insert(EmployerDao Dao){
//        Dao.save(new Employer("Валуев","Георгий","Леонидович","8(444)456-78-90","Москва","Московская",20));
//    }
//    static void update(EmployerDao Dao){Dao.update(new Employer(4,"Dff","Георгий","Леонидович","8(444)456-78-90","Москва","Московская",20,6));}
//    static void delete(EmployerDao Dao){long j = 4; Dao.deleteById(j);}
//    static Collection<Employer> selectall(EmployerDao Dao){return  Dao.findALl();}
//
//}
