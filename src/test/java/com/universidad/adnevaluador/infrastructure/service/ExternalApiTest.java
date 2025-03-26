package com.universidad.adnevaluador.infrastructure.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExternalApiTest {

    @Autowired
    private ExternalApi externalApi;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testGetInfoExternalApi() throws Exception {
        String result = externalApi.getInfoExternalApi();

        assertNotNull(result);

        ResponseEntity<String> response = restTemplate.getForEntity("https://restcountries.com/v3.1/all", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(result, response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result);
        assertTrue(jsonNode.isArray());
        assertFalse(jsonNode.isEmpty());
        assertTrue(jsonNode.get(0).has("name"));
    }

}