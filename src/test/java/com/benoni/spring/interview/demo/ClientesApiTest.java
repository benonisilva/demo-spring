package com.benoni.spring.interview.demo;

import javax.inject.Inject;

import com.benoni.spring.interview.demo.api.model.Cidade;
import com.benoni.spring.interview.demo.repositories.CidadesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

@ContextConfiguration
@SpringBootTest(classes = DemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application-test.properties")
public class ClientesApiTest extends AbstractTestApi {

    @Inject
    private CidadesRepository cidadeRepository;

    ClientesApiTest() {
        setWebTestClient(WebTestClient.bindToServer().baseUrl(this.getEndpoint()).build());
    }

    @Test
    void can_list_cidades() {
        get("/clientes");
        getResponseSpec().expectStatus().is2xxSuccessful();
        getResponseSpec().expectBody().jsonPath("$[0].nome").isEqualTo("morador altamira");
    }

    // @Test
    // void can_search_by_name_cidade() {
    // get("/cidades?nome=altamira");
    // getResponseSpec().expectStatus().is2xxSuccessful();
    // getResponseSpec().expectBody().jsonPath("$[0].nome").isEqualTo("altamira");
    // }

    // @Test
    // void can_search_by_id_estado() {
    // get("/cidades?estadoId=1");
    // getResponseSpec().expectStatus().is2xxSuccessful();
    // getResponseSpec().expectBody().jsonPath("$[0].nome").isEqualTo("altamira");
    // }

    // @Test
    // void when_nome_not_result_array_empty() {
    // get("/cidades?nome=barra");
    // getResponseSpec().expectStatus().is2xxSuccessful();
    // getResponseSpec().expectBody().jsonPath("$.length()").isEqualTo(0);
    // }

    // @Test
    // void cant_insert_when_cidade_not_have_nome() {
    // post("/cidades", "cidade_invalid_nome.json", Cidade.class);
    // getResponseSpec().expectStatus().is4xxClientError();
    // }

    // @Test
    // void can_insert_cidade_nova_altamira() {
    // post("/cidades", "cidade_nova_altamira.json", Cidade.class);
    // getResponseSpec().expectStatus().is2xxSuccessful();
    // getResponseSpec().expectBody().jsonPath("$.nome").isEqualTo("nova altamira");
    // }

    // @Test
    // void can_insert_cidade_without_error() {
    // post("/cidades", "cidade_invalid_nome.json", Cidade.class);
    // getResponseSpec().expectStatus().is4xxClientError();
    // }

}
