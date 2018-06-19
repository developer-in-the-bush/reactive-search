package com.reactivesearch;

import com.reactivesearch.configuration.ReactiveSearchApplication;
import com.reactivesearch.rest.domain.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReactiveSearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReactiveSearchRestSmokes {

    private final static String STUB_CONTENT = "Hello I am Content";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testListAll() {
        final ResponseEntity<String> listAllResponse = this.testRestTemplate.getForEntity("/documents", String.class);
        assertThat(listAllResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCreate() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(1);
        parameters.add("content", STUB_CONTENT);
        final ResponseEntity<Document> createEntity = this.testRestTemplate.postForEntity("/documents", parameters, Document.class);
        assertThat(createEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(createEntity.getBody().getTokens()).isEqualTo(STUB_CONTENT);
    }

    @Test
    public void testGetSingle() {
        testCreate(); // Guarantees that get endpoint will return entity with id 0
        final ResponseEntity<Document> responseEntity = this.testRestTemplate.getForEntity("/documents/0", Document.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isEqualTo(0);
        assertThat(responseEntity.getBody().getTokens()).isEqualTo(STUB_CONTENT);
    }
}
