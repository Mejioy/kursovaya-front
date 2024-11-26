package ru.kafpin.lr6_bzz.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kafpin.lr6_bzz.MainApplication;
import ru.kafpin.lr6_bzz.domains.ProvidedService;
import ru.kafpin.lr6_bzz.utils.DBHelper;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * Класс ProvidedServiceDao со свойством property.
 * <p>
 *     Данный класс разработан как реализация интерфейса Dao для сущности Оказанная Услуга.
 * </p>
 * @author Ярослав Кокурин
 * @version 1.0
 */
public class ProvidedServiceDao implements Dao<ProvidedService, Long> {
    /** Поле свойство*/
    private Properties property;
    private ResourceBundle bundle = ResourceBundle.getBundle("employer", Locale.getDefault());
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);
    /**
     * Конструктор – создание нового экземпляра
     * @see ProvidedServiceDao#ProvidedServiceDao()
     */
    public ProvidedServiceDao() throws Exception {
        URL url = this.getClass()
                        .getResource("/ru/kafpin/lr6_bzz/providedservice.properties");
        this.property = new Properties();
        FileInputStream fis = null;
        if(url==null)
            throw new Exception();
        try {
            fis = new FileInputStream(url.getFile());
            property.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Функция получения коллекции {@link ProvidedService} из БД
     * @return возвращает коллекцию {@link ProvidedService}
     */
    @Override
    public Collection<ProvidedService> findALl() {
        List<ProvidedService> list = null;
        ResultSet rs = null;
        try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.select"))){
            rs = statement.executeQuery();
            list = mapper(rs);
        }
        catch (SQLException e){
            logger.error(bundle.getString("querryerror"),e);
            System.out.println(e.getMessage());
        }
        logger.debug(bundle.getString("searchprovided"));
        return list;
    }
    /**
     * Функция получения коллекции {@link ProvidedService} из БД
     * @return возвращает коллекцию {@link ProvidedService}
     */
    public Collection<ProvidedService> findALlFromTo(java.util.Date from, java.util.Date to) {
        List<ProvidedService> list = null;
        ResultSet rs = null;
        try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.selectFromAndTo"))){
            statement.setDate(1, new Date(from.getTime()));
            statement.setDate(2,new Date(to.getTime()));
            rs = statement.executeQuery();
            list = mapper(rs);
        }
        catch (SQLException e){
            logger.error(bundle.getString("querryerror"),e);
            System.out.println(e.getMessage());
        }
        logger.debug(bundle.getString("searchprovided"));
        return list;
    }
    /**
     * Функция преобразования результирующего набора(выборки) в коллекцию {@link ProvidedService}
     * @param rs результирующий набор
     * @return возвращает коллекцию {@link ProvidedService}
     */
    protected List<ProvidedService> mapper(ResultSet rs){
        List<ProvidedService> list = new ArrayList<>();
        try {
            while (rs.next()){
                list.add(new ProvidedService(
                        rs.getInt("id"),
                        rs.getInt("idservice"),
                        rs.getInt("idemployee"),
                        rs.getInt("idautomobile"),
                        rs.getDate("datatime")
                ));
            }
        }
        catch (SQLException e){
            logger.error(bundle.getString("querryerror"),e);
            System.out.println(e.getMessage());
        }
        logger.debug(bundle.getString("rsparseprovided"));
        return list;
    }
    /**
     * Функция добавления {@link ProvidedService} в БД
     * @param providedService сущность {@link ProvidedService}
     * @return возвращает сущность {@link ProvidedService}
     */
    @Override
    public ProvidedService save(ProvidedService providedService) {
            try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.insert"))){
                statement.setLong(1,providedService.getIdservice());
                statement.setLong(2,providedService.getIdemployer());
                statement.setLong(3,providedService.getIdautomobile());
                statement.setDate(4,providedService.getSqlDateTime());
            statement.executeUpdate();
        }
        catch (SQLException e){
            logger.error(bundle.getString("querryerror"),e);
            System.out.println(e.getMessage());
        }
        logger.debug(bundle.getString("addquerryprovided"));
        return providedService;
    }
    /**
     * Функция изменения {@link ProvidedService} в БД
     * @param providedService сущность {@link ProvidedService}
     * @return возвращает сущность {@link ProvidedService}
     */
    @Override
    public ProvidedService update(ProvidedService providedService) {
        try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.update"))){
            statement.setLong(1,providedService.getIdservice());
            statement.setLong(2,providedService.getIdemployer());
            statement.setLong(3,providedService.getIdautomobile());
            statement.setDate(4,providedService.getSqlDateTime());
            statement.setLong(5,providedService.getProvidedServiceID());
            statement.executeUpdate();
        }
        catch (SQLException e){
            logger.error(bundle.getString("querryerror"),e);
            System.out.println(e.getMessage());
        }
        logger.debug(bundle.getString("editquerryprovided"));
        return providedService;
    }
    /**
     * Функция удаления записи об Оказанной Услуге из БД
     * @param aLong идентификатор Оказанной Услуги
     */
    @Override
    public void deleteById(Long aLong) {
        try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.delete"))){
            statement.setLong(1,aLong);
            statement.execute();
        }
        catch (SQLException e){
            logger.error(bundle.getString("querryerror"),e);
            System.out.println(e.getMessage());
        }
        logger.debug(bundle.getString("deleteprovided"));
    }
    /**
     * Функция поиска экземпляра {@link ProvidedService} в БД
     * @param aLong идентификатор Оказанной услуги
     * @return возвращает сущность {@link ProvidedService}
     */
    @Override
    public ProvidedService findById(Long aLong) {
        return null;
    }
}
