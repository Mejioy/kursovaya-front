package ru.kafpin.lr6_bzz.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import ru.kafpin.lr6_bzz.domains.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static ru.kafpin.lr6_bzz.MainApplication.encodedAuth;

@NoArgsConstructor
public class ServiceDao implements Dao<Service, Long> {

    private URL url;
    private final ObjectMapper mapper = new ObjectMapper();
    private HttpURLConnection conn;

    @Override
    public Collection<Service> findALl() {
        try {
            url = new URL("http://127.0.0.1:8080/api/services");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            if(200 != conn.getResponseCode()){
                System.out.printf("Response code = "+conn.getResponseCode());
                return null;
            }
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        List<Service> list = null;
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
                    .forType(new TypeReference<List<Service>>() {})
                    .readValue(content.toString());
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of parsing");
        }
        return list;
    }

    @Override
    public Service save(Service service) {
        String json = parseSingleServiceToJson(service);

        try {
            url = new URL("http://127.0.0.1:8080/api/services");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            conn.setDoOutput(true);
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        writeResponseToJson(json);
        return parseJsonToSingleService();
    }

    @Override
    public Service update(Service service) {
        String json = parseSingleServiceToJson(service);
        try {
            url = new URL("http://127.0.0.1:8080/api/services/"+service.getId());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            conn.setDoOutput(true);
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        writeResponseToJson(json);
        return parseJsonToSingleService();
    }

    @Override
    public void deleteById(Long id) {
        try {
            url = new URL("http://127.0.0.1:8080/api/services/"+id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            if(200 != conn.getResponseCode()){
                System.out.printf("Response code = " + conn.getResponseCode());
            }
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
    }

    @Override
    public Service findById(Long id) {
        try {
            url = new URL("http://127.0.0.1:8080/api/services/"+id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            if(200 != conn.getResponseCode()){
                System.out.printf("Response code = "+conn.getResponseCode());
                return null;
            }
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        return parseJsonToSingleService();
    }

    public Service findByName(String name) {
        try {
            url = new URL("http://127.0.0.1:8080/api/services/name");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            conn.setRequestProperty("Content-Type", "application/text");
            conn.setDoOutput(true);
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = name.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            System.out.println(name);
            if(200 != conn.getResponseCode()){
                System.out.printf("Response code = "+conn.getResponseCode());
                return null;
            }
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        return parseJsonToSingleService();
    }
    private void writeResponseToJson(String json){
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            System.out.println("error of write outputstream");
        }
    }
    private String parseSingleServiceToJson(Service service){
        String json = null;
        try {
            json = mapper.writeValueAsString(service);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of write in json");
        }
        return json;
    }
    private Service parseJsonToSingleService(){
        Service service = null;
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
            service = mapper.reader()
                    .forType(Service.class)
                    .readValue(response.toString());
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of parsing");
        }

        System.out.println("response "+service);
        return service;
    }
}
