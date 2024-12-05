package ru.kafpin.lr6_bzz.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import ru.kafpin.lr6_bzz.domains.Service;
import ru.kafpin.lr6_bzz.utils.DBHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@NoArgsConstructor
public class ServiceDao implements Dao<Service, Long> {
    private ResourceBundle bundle = ResourceBundle.getBundle("administrator", Locale.getDefault());


    @Override
    public Collection<Service> findALl() {
        List<Service> list = null;
        URL url;
        HttpURLConnection conn = null;
        try {
            url = new URL("http://127.0.0.1:8080/api/services");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if(200 != conn.getResponseCode()){
                return null;
            }
        }
        catch (IOException e) {
        }

        StringBuilder content = new StringBuilder();
            try(BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()))){
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                    content.append("\n");
                }
            } catch (IOException e) {
                System.out.println("bufferReader");
            }
        System.out.println(content);
        ObjectMapper mapper = new ObjectMapper();


        try {
            list = mapper.reader()
                    .forType(new TypeReference<List<Service>>() {})
                    .readValue(content.toString());
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of parsing");
        }
        return list;
    }

    /**
     * Функция преобразования результирующего набора(выборки) в коллекцию {@link Service}
     * @param rs результирующий набор
     * @return возвращает коллекцию {@link Service}
     */
    protected List<Service> mapper(ResultSet rs){
        List<Service> list = new ArrayList<>();
        try {
            while (rs.next()){
                list.add(new Service(
                        rs.getInt("id"),
                        rs.getString("name"),
//                        rs.getInt("duration"),
                        rs.getInt("price"),
                        rs.getString("description")
                ));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }


    /**
     * Функция добавления {@link Service} в БД
     * @param service сущность {@link Service}
     * @return возвращает сущность {@link Service}
     */
    @Override
    public Service save(Service service) {
            try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.insert"))){
            statement.setString(1,service.getName());
//            statement.setInt(2,service.getDuration());
            statement.setInt(2,service.getPrice());
            if(service.getDescription()!=null&&!service.getDescription().trim().isEmpty())
                statement.setString(3,service.getDescription());
            else
                statement.setNull(3, Types.VARCHAR);
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return service;
    }
    /**
     * Функция изменения {@link Service} в БД
     * @param service сущность {@link Service}
     * @return возвращает сущность {@link Service}
     */
    @Override
    public Service update(Service service) {
        try(PreparedStatement statement = DBHelper.getConnection().prepareStatement(property.getProperty("sql.update"))){
            statement.setString(1,service.getName());
//            statement.setInt(2,service.getDuration());
            statement.setInt(2,service.getPrice());
            if(service.getDescription()!=null&&!service.getDescription().trim().isEmpty())
                statement.setString(3,service.getDescription());
            else
                statement.setNull(3, Types.VARCHAR);
            statement.setLong(4,service.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return service;
    }
    /**
     * Функция удаления записи об Услуге из БД
     * @param aLong идентификатор Услуги
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
     * Функция поиска экземпляра {@link Service} в БД
     * @param aLong идентификатор Услуги
     * @return возвращает сущность {@link Service}
     */
    @Override
    public Service findById(Long aLong) {
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
     * Функция получения экземпляра {@link Service} из БД
     * @param rs результирующий набор
     * @return возвращает сущность {@link Service}
     */
    protected Service getEntity(ResultSet rs){
        Service service = null;
        try {
            while (rs.next()){
                service = new Service(
                        rs.getInt("id"),
                        rs.getString("name"),
//                        rs.getInt("duration"),
                        rs.getInt("price"),
                        rs.getString("description")
                );
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return service;
    }
}
