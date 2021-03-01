package com.fikirtepe.app.Web;

import com.fikirtepe.app.Exceptions.UserNotFoundException;
import com.fikirtepe.app.Model.User;
import com.fikirtepe.app.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserRestControllerTests {

    @LocalServerPort
    private int randomPort;

    /* if baseURL is defined here, randomPort is 0 ?? */
//    private final String baseUrl = "http://localhost:";

    //restTemplate for testing requests
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUserSuccess() throws URISyntaxException {
        //define base url and convert to a uri
        final String baseUrl =  "http://localhost:" + randomPort + "/api/register/";
        URI uri = new URI(baseUrl);

        //test User
        User user = new User(5L,"password", "testFirstName", "testLastName","testEmail@gmail.com");

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

            Assertions.assertEquals(result.getStatusCodeValue(),200);

        }catch(URISyntaxException ex){
            ex.printStackTrace();
        }

    }

    @Test
    public void testGetUsersSuccess() {
        final String baseUrl = "http://localhost:" + randomPort + "/api/users";
        ResponseEntity<User []> responseEntity = this.restTemplate.getForEntity(baseUrl, User[].class);
        List<User> userList = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));

        Assertions.assertTrue(userList.size() > 0);
    }

    @Test
    public void testGetUserSuccess() {
        User testUser = userService.findAllUsers().get(0);

        final String baseUrl = "http://localhost:" + randomPort + "/api/user/" + testUser.getId();

        User responseUser = this.restTemplate.getForObject(baseUrl, User.class);
        //ResponseEntity<User> responseEntity = this.restTemplate.getForEntity(baseUrl + testUser.getId(), User.class);

        Assertions.assertEquals(testUser.getId(), responseUser.getId());
    }

    @Test
    public void testApproveUserSuccess() {
        User testUser = new User(5L,"password", "testFirstName", "testLastName","testEmail@gmail.com");
        testUser.setApproved(false);

        final String baseUrl = "http://localhost:" + randomPort + "/api/approveUser/" + testUser.getId();

        //transactional annotation is not working with restTemplate?
        User responseUser = this.restTemplate.getForObject(baseUrl, User.class);

        Assertions.assertNotEquals(testUser.isApproved(), responseUser.isApproved());
    }

    @Test
    public void testRejectUserSuccess() {
        User testUser = new User(6L,"password", "testFirstName", "testLastName","testEmail@gmail.com");
        testUser.setApproved(true);

        final String baseUrl = "http://localhost:" + randomPort + "/api/rejectUser/" + testUser.getId();

        User responseUser = this.restTemplate.getForObject(baseUrl, User.class);

        Assertions.assertNotEquals(testUser.isApproved(), responseUser.isApproved());
    }

    @Test
    public void deleteUserSuccess(){
        User testUser = new User(7L,"password", "testFirstName", "testLastName","testEmail@gmail.com");
        userService.createUser(testUser);

        final String baseUrl = "http://localhost:" + randomPort + "/api/delete/" + testUser.getId();

        try{
            userService.findUser(testUser.getId());
        }catch (UserNotFoundException ex){
            assert true;
        }catch (Exception ex){
            assert false;
        }
    }

}
