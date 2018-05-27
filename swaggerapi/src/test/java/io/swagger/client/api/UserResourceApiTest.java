package io.swagger.client.api;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ManagedUserVM;
import io.swagger.client.model.ResponseEntity;
import io.swagger.client.model.User;
import io.swagger.client.model.UserDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UserResourceApi
 */
public class UserResourceApiTest {

    private UserResourceApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(UserResourceApi.class);
    }

    
    /**
     * createUser
     *
     * 
     */
    @Test
    public void createUserUsingPOSTTest() {
        ManagedUserVM managedUserVM = null;
        // ResponseEntity response = api.createUserUsingPOST(managedUserVM);

        // TODO: test validations
    }
    
    /**
     * deleteUser
     *
     * 
     */
    @Test
    public void deleteUserUsingDELETETest() {
        String login = null;
        // Void response = api.deleteUserUsingDELETE(login);

        // TODO: test validations
    }
    
    /**
     * getAllUsers
     *
     * 
     */
    @Test
    public void getAllUsersUsingGETTest() {
        Integer page = null;
        Integer size = null;
        List<String> sort = null;
        // List<UserDTO> response = api.getAllUsersUsingGET(page, size, sort);

        // TODO: test validations
    }
    
    /**
     * getAuthorities
     *
     * 
     */
    @Test
    public void getAuthoritiesUsingGETTest() {
        // List<String> response = api.getAuthoritiesUsingGET();

        // TODO: test validations
    }
    
    /**
     * getUser
     *
     * 
     */
    @Test
    public void getUserUsingGETTest() {
        String login = null;
        // UserDTO response = api.getUserUsingGET(login);

        // TODO: test validations
    }
    
    /**
     * search
     *
     * 
     */
    @Test
    public void searchUsingGETTest() {
        String query = null;
        // List<User> response = api.searchUsingGET(query);

        // TODO: test validations
    }
    
    /**
     * updateUser
     *
     * 
     */
    @Test
    public void updateUserUsingPUTTest() {
        ManagedUserVM managedUserVM = null;
        // UserDTO response = api.updateUserUsingPUT(managedUserVM);

        // TODO: test validations
    }
    
}
