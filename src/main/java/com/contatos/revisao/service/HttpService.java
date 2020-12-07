package com.contatos.revisao.service;

import com.contatos.revisao.model.Contato;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HttpService {

    private static HttpService INSTANCE = null;
    private final String URI_BASE = "https://pss-apirest-contato.herokuapp.com/api/v1/contatos/";
    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    private HttpService() {}
    
    public static HttpService instance() {
        if (INSTANCE == null) {
            INSTANCE = new HttpService();
        }
        return INSTANCE;
    } 

    public Contato sendGetRequest(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(URI_BASE + id))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), Contato.class);
    }

    public List<Contato> sendGetListRequest() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(URI_BASE))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, Contato.class));
    }
    
    public Contato sendPostRequest(Contato contato) throws JsonProcessingException, InterruptedException, ExecutionException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(contato);
        
        HttpRequest request = HttpRequest.newBuilder()
                .POST(BodyPublishers.ofString(requestBody))
                .uri(URI.create(URI_BASE))
                .header("Content-Type", "application/json")
                .build();
        
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        
        return new ObjectMapper().readValue(response.body(), Contato.class);
    }
    
    public Contato sendPutRequest(Contato contato) throws JsonProcessingException, InterruptedException, ExecutionException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(contato);
        
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(BodyPublishers.ofString(requestBody))
                .uri(URI.create(URI_BASE + contato.getId()))
                .header("Content-Type", "application/json")
                .build();
        
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        
        return new ObjectMapper().readValue(response.body(), Contato.class);
    }
    
    public int sendDeleteRequest(String id) throws JsonProcessingException, InterruptedException, ExecutionException, IOException {        
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(URI_BASE + id))
                .header("Accept", "application/json")
                .build();
        
        return HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString())
                .statusCode();
    }
}
