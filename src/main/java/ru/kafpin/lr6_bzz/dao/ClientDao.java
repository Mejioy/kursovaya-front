package ru.kafpin.lr6_bzz.dao;
import ru.kafpin.lr6_bzz.domains.Client;
import ru.kafpin.lr6_bzz.utils.DBHelper;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Класс ClientDao со свойством property.
 * <p>
 *     Данный класс разработан как реализация интерфейса Dao для сущности Клиент.
 * </p>
 * @author Ярослав Кокурин
 * @version 1.0
 */
public class ClientDao implements Dao<Client, Long> {
    /** Поле свойство*/
    private Properties property;
    private ResourceBundle bundle = ResourceBundle.getBundle("employer", Locale.getDefault());
    /**
     * Конструктор – создание нового экземпляра
     * @see ClientDao#ClientDao()
     */
    public ClientDao() throws Exception {
        URL url = this.getClass()
                        .getResource("/ru/kafpin/lr6_bzz/client.properties");
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
     * Функция получения коллекции {@link Client} из БД
     * @return возвращает коллекцию {@link Client}
     */
    @Override
    public Collection<Client> findALl() {
        List<Client> list = null;
        ResultSet rs = null;
        try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.select"))){
            rs = statement.executeQuery();
            list = mapper(rs);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    /**
     * Функция преобразования результирующего набора(выборки) в коллекцию {@link Client}
     * @param rs результирующий набор
     * @return возвращает коллекцию {@link Client}
     */
    protected List<Client> mapper(ResultSet rs){
        List<Client> list = new ArrayList<>();
        try {
            while (rs.next()){
                list.add(new Client(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("patronym"),
                        rs.getString("phone")));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    /**
     * Функция добавления {@link Client} в БД
     * @param client сущность {@link Client}
     * @return возвращает сущность {@link Client}
     */
    @Override
    public Client save(Client client) {
            try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.insert"))){
            statement.setString(1,client.getSurName());
            statement.setString(2,client.getName());
            statement.setString(3,client.getPatronym());
            statement.setString(4,client.getPhone());
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return client;
    }
    /**
     * Функция изменения {@link Client} в БД
     * @param client сущность {@link Client}
     * @return возвращает сущность {@link Client}
     */
    @Override
    public Client update(Client client) {
        try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.update"))){
            statement.setString(1,client.getSurName());
            statement.setString(2,client.getName());
            statement.setString(3,client.getPatronym());
            statement.setString(4,client.getPhone());
            statement.setLong(5,client.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return client;
    }
    /**
     * Функция удаления записи о Клиенте из БД
     * @param aLong идентификатор Клиента
     */
    @Override
    public void deleteById(Long aLong) {
        try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.delete"))){
            statement.setLong(1,aLong);
            statement.execute();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Функция поиска экземпляра {@link Client} в БД
     * @param aLong идентификатор Клиента
     * @return возвращает сущность {@link Client}
     */
    @Override
    public Client findById(Long aLong) {
        ResultSet rs = null;
        try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.findbyid"))){
            statement.setLong(1,aLong);
            rs = statement.executeQuery();
            return getEntity(rs);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * Функция получения экземпляра {@link Client} из БД
     * @param rs результирующий набор
     * @return возвращает сущность {@link Client}
     */
    protected Client getEntity(ResultSet rs){
        Client client = null;
        try {
            while (rs.next()){
                client = new Client(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("patronym"),
                        rs.getString("phone")
                );
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return client;
    }
}
