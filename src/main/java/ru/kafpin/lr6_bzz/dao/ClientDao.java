package ru.kafpin.lr6_bzz.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import ru.kafpin.lr6_bzz.domains.Client;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@NoArgsConstructor
public class ClientDao implements Dao<Client, Long> {
    private URL url;
    private final ObjectMapper mapper = new ObjectMapper();
    private HttpURLConnection conn;
    @Override
    public Collection<Client> findALl() {
        try {
            url = new URL("http://127.0.0.1:8080/api/clients");
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
        List<Client> list = null;
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
                    .forType(new TypeReference<List<Client>>() {})
                    .readValue(content.toString());
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of parsing");
        }
        return list;
    }

    @Override
    public Client save(Client client) {
        String json = parseSingleClientToJson(client);

        try {
            url = new URL("http://127.0.0.1:8080/api/clients/add");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        writeResponseToJson(json);
        return parseJsonToSingleClient();
    }

    @Override
    public Client update(Client client) {
        String json = parseSingleClientToJson(client);
        try {
            url = new URL("http://127.0.0.1:8080/api/clients/"+client.getId());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
        }
        catch (IOException e) {
            System.out.println("URL/Connection error");
        }
        writeResponseToJson(json);
        return parseJsonToSingleClient();
    }

    @Override
    public void deleteById(Long id) {
        try {
            url = new URL("http://127.0.0.1:8080/api/clients/"+id);
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
    public Client findById(Long id) {
        try {
            url = new URL("http://127.0.0.1:8080/api/clients/"+id);
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
        return parseJsonToSingleClient();
    }

    private void writeResponseToJson(String json){
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            System.out.println("error of write outputstream");
        }
    }
    private String parseSingleClientToJson(Client client){
        String json = null;
        try {
            json = mapper.writeValueAsString(client);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of write in json");
        }
        return json;
    }
    private Client parseJsonToSingleClient(){
        Client client = null;
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
            client = mapper.reader()
                    .forType(Client.class)
                    .readValue(response.toString());
        } catch (JsonProcessingException e) {
            System.out.println(e);
            System.out.println("Error of parsing");
        }

        System.out.println("response "+client);
        return client;
    }
}
