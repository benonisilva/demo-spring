package com.benoni.spring.interview.demo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

public abstract class AbstractTestApi {

    private static String basePath = "/api/v1/";
    private static String HOST = "localhost";
    private static final String DIRETORIO_JSON_RESPONSE = "/jsons/";

    private String path;

    @LocalServerPort
    private int port;

    AbstractTestApi(String resourcePath) {
        path = resourcePath;
    }

    public AbstractTestApi() {

    }

    @Inject
    private WebTestClient webTestClient;

    private WebTestClient.ResponseSpec responseSpec;

    protected String getEndpoint() {
        return String.format("http://%s:%s/", HOST, port);
    }

    protected void setResponseSpec(WebTestClient.ResponseSpec responseSpec) {
        this.responseSpec = responseSpec;
    }

    /**
     * @return the webTestClient
     */
    public WebTestClient getWebTestClient() {
        return webTestClient;
    }

    /**
     * @param webTestClient the webTestClient to set
     */
    public void setWebTestClient(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    /**
     * @return the responseSpec
     */
    public WebTestClient.ResponseSpec getResponseSpec() {
        return responseSpec;
    }

    /**
     * Set ResponseSpec using return of post
     * 
     * @param <T>          bodyType input
     * @param uri          uri. exemple: /resource/type
     * @param jsonfilePath path to jsonfile
     * @param type         bodyType
     */
    public <T> void post(String uri, String jsonfilePath, Class<T> type) {
        try {
            String pathToFile = DIRETORIO_JSON_RESPONSE + jsonfilePath;
            URL absolutePath = Thread.currentThread().getContextClassLoader().getResource(".");
            ObjectMapper objectmapper = createObjectMapper();
            URI pathUri = new URL(absolutePath.toString() + pathToFile).toURI();
            JsonNode node = objectmapper.readTree(new File(pathUri));
            Object bodyObject = objectmapper.treeToValue(node, type);
            setResponseSpec(getWebTestClient().post().uri(basePath + uri).bodyValue(bodyObject)
                    .header(header(), contentType()).exchange());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private String contentType() {
        return MediaType.APPLICATION_JSON_VALUE;
    }

    private String header() {
        return HttpHeaders.CONTENT_TYPE;
    }

    /**
     * Set ResponseSpec using return of put
     * 
     * @param <T>          bodyType input
     * @param uri          uri. exemple: /resource/type
     * @param jsonfilePath path to jsonfile
     * @param type         bodyType
     */
    public <T> void put(String uri, String jsonfilePath, Class<T> type) {
        try {
            String pathToFile = this.getClass().getClassLoader().getResource(DIRETORIO_JSON_RESPONSE + jsonfilePath)
                    .toString();
            ObjectMapper objectmapper = createObjectMapper();
            JsonNode node = objectmapper.readTree(new File(pathToFile));
            Object bodyObject = objectmapper.treeToValue(node, type);
            setResponseSpec(
                    getWebTestClient().put().uri(uri).bodyValue(bodyObject).header(header(), contentType()).exchange());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper objectmapper = new ObjectMapper();
        objectmapper.registerModules(new JodaModule());
        objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectmapper;

    }

    /**
     * Set ResponseSpec using return of post
     * 
     * @param uriPath uri exemple: /resource/type
     */
    public void get(String uriPath) {
        String resourcePath = basePath + uriPath;
        setResponseSpec(getWebTestClient().get().uri(resourcePath).header(header(), contentType()).exchange());
    }
}
