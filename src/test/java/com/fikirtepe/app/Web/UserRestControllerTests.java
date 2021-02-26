package com.fikirtepe.app.Web;

import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.hamcrest.collection.IsEmptyCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Transactional
public class UserRestControllerTests {

    @LocalServerPort
    private int randomPort;

    /* if baseURL is defined here, randomPort is 0 ?? */

    //restTemplate for testing requests
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Test @Transactional
    public void testCreateUserSuccess() throws URISyntaxException {

        //define base url and convert to a uri
        final String baseUrl = "http://localhost:" + randomPort + "/api/register/";
        URI uri = new URI(baseUrl);

        //test User
        User user = new User(5L,"testFirstName", "testLastName","testEmail@gmail.com");

        //define a request with test User
        HttpEntity<User> request = new HttpEntity<>(user);

        //view the result of the post request with restTemplate to uri
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //verify request succeed
        Assertions.assertTrue(result.getStatusCodeValue() == 201 || result.getStatusCodeValue() == 409);

        //transaction did not rollback ?
    }

    @Test
    public void testValidateUserSuccess(){

        final String baseUrl = "http://localhost:" + randomPort + "/api/login";
        try {
            URI uri = new URI(baseUrl);
            List<User> userList = userService.findAllUsers();

            //create request for first user as sample
            HttpEntity<User> request = new HttpEntity<>(userList.get(0));

            //post a request for that user and expect 200
            ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

            Assertions.assertTrue(result.getStatusCodeValue() == 200);

        }catch(URISyntaxException ex){
            ex.printStackTrace();
        }

    }
}
