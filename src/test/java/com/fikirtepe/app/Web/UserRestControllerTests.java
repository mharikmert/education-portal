package com.fikirtepe.app.Web;


import com.fikirtepe.app.Model.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestControllerTests {

    @LocalServerPort
    private int randomPort;

    //restTemplate for testing requests
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateUserSuccess() throws URISyntaxException {
        //define base url and convert to a uri
        final String baseUrl = "http://localhost:" + randomPort + "/api";

        URI uri = new URI(baseUrl);

        //test User
        User user = new User(1L,"testFirstName", "testLastName");

        //define a request with test User
        HttpEntity<User> request = new HttpEntity<>(user);

        //view the result of the post request with restTemplate to uri
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //verify request succeed
        MatcherAssert.assertThat(result.getStatusCodeValue(), Matchers.equalTo(201));
    }
}
