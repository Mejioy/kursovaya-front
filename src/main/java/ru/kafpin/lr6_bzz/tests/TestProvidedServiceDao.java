//package ru.kafpin.lr6_bzz.tests;
//
//
//import ru.kafpin.lr6_bzz.dao.ProvidedServiceDao;
//
//import ru.kafpin.lr6_bzz.domains.ProvidedService;
//
//import java.util.Date;
//import java.util.Collection;
//
//public class TestProvidedServiceDao {
//    public static void main(String[] args) {
//        ProvidedServiceDao Dao = null;
//        try {
//            Dao = new ProvidedServiceDao();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
////        delete(Dao);
//        Collection<ProvidedService> al = selectall(Dao);
//        for (ProvidedService cl :al
//             ) {
//            System.out.println(cl);
//        }
//    }
//    static void insert(ProvidedServiceDao Dao){Dao.save(new ProvidedService(7,3,11,new Date()));
//    }
//    static void update(ProvidedServiceDao Dao){Dao.update(new ProvidedService(38,7,3,11,new Date()));}
//    static void delete(ProvidedServiceDao clientDao){long j = 39; clientDao.deleteById(j);}
//    static Collection<ProvidedService> selectall(ProvidedServiceDao Dao){return  Dao.findALl();}
//
////    static Collection<ProvidedService> selectallFromTo(ProvidedServiceDao Dao){return  Dao.findALlFromTo();}
//
//}
