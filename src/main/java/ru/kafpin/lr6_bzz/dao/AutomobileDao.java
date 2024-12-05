package ru.kafpin.lr6_bzz.dao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import ru.kafpin.lr6_bzz.domains.Automobile;
import ru.kafpin.lr6_bzz.domains.Client;
import ru.kafpin.lr6_bzz.utils.DBHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@NoArgsConstructor
public class AutomobileDao implements Dao<Automobile, Long> {
    private URL url;
    private final ObjectMapper mapper = new ObjectMapper();
//    private HttpURLConnection conn;

    @Override
    public Collection<Automobile> findALl() {
        HttpURLConnection conn = null;
        try {
            url = new URL("http://127.0.0.1:8080/api/automobiles");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if(200 != conn.getResponseCode()){
                System.out.printf("Response code = "+conn.getResponseCode());
                return null;
            }
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }

        return parseJsonToListAutomobiles(conn);
    }

    public Collection<Automobile> findALlCarsOfOwner(Long id) {
        HttpURLConnection conn = null;
        try {
            url = new URL("http://127.0.0.1:8080/api/automobilesofowner/"+id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if(200 != conn.getResponseCode()){
                System.out.printf("Response code = "+conn.getResponseCode());
                return null;
            }
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        return parseJsonToListAutomobiles(conn);
    }

    @Override
    public Automobile save(Automobile automobile) {
        HttpURLConnection conn = null;
        String json = parseSingleAutomobileToJson(automobile);

        try {
            url = new URL("http://127.0.0.1:8080/api/automobiles/add");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        writeResponseToJson(json, conn);
        return parseJsonToSingleAutomobile(conn);
    }

    @Override
    public Automobile update(Automobile automobile) {
        HttpURLConnection conn = null;
        String json = parseSingleAutomobileToJson(automobile);
        try {
            url = new URL("http://127.0.0.1:8080/api/automobiles/"+automobile.getId());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        writeResponseToJson(json,conn);
        return parseJsonToSingleAutomobile(conn);
    }

    @Override
    public void deleteById(Long id) {
        HttpURLConnection conn;
        try {
            url = new URL("http://127.0.0.1:8080/api/automobiles/"+id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");
            if(200 != conn.getResponseCode()){
                System.out.printf("Response code = " + conn.getResponseCode());
            }
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
    }

    @Override
    public Automobile findById(Long id) {
        HttpURLConnection conn = null;
        try {
            url = new URL("http://127.0.0.1:8080/api/automobiles/"+id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if(200 != conn.getResponseCode()){
                System.out.printf("Response code = "+conn.getResponseCode());
                return null;
            }
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        return parseJsonToSingleAutomobile(conn);
    }

    private void writeResponseToJson(String json, HttpURLConnection conn){
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            System.out.println("error of write outputstream");
        }
    }
    private String parseSingleAutomobileToJson(Automobile automobile){
        String json = null;
        try {
            json = mapper.writeValueAsString(automobile);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of write in json");
        }
        return json;
    }
    private Automobile parseJsonToSingleAutomobile(HttpURLConnection conn){
        Automobile automobile = null;
        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine);
                response.append("\n");
            }
        } catch (IOException e) {
            System.out.println("bufferReader error");
        }

        System.out.println("response "+response);

        try {
            automobile = mapper.reader()
                    .forType(Automobile.class)
                    .readValue(response.toString());
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of parsing");
        }

        System.out.println("response "+automobile);
        return automobile;
    }
    private List<Automobile> parseJsonToListAutomobiles(HttpURLConnection conn){
        List<Automobile> list = null;
        StringBuilder content = new StringBuilder();
        try(BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))){
            String line;
            while((line = bufferedReader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
        } catch (IOException e) {
            System.out.println("bufferReader error");
        }

        try {
            list = mapper.reader()
                    .forType(new TypeReference<List<Automobile>>() {})
                    .readValue(content.toString());
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of parsing");
        }
        return list;
    }
}
