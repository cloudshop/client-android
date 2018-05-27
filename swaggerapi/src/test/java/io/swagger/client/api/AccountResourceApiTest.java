package io.swagger.client.api;

import io.swagger.client.ApiClient;
import io.swagger.client.model.KeyAndPasswordVM;
import io.swagger.client.model.ManagedUserVM;
import io.swagger.client.model.ResponseEntity;
import io.swagger.client.model.UserDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for AccountResourceApi
 */
public class AccountResourceApiTest {

    private AccountResourceApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(AccountResourceApi.class);
    }

    
    /**
     * activateAccount
     *
     * 
     */
    @Test
    public void activateAccountUsingGETTest() {
        String key = null;
        // String response = api.activateAccountUsingGET(key);

        // TODO: test validations
    }
    
    /**
     * changePassword
     *
     * 
     */
    @Test
    public void changePasswordUsingPOSTTest() {
        String password = null;
        // ResponseEntity response = api.changePasswordUsingPOST(password);

        // TODO: test validations
    }
    
    /**
     * finishPasswordReset
     *
     * 
     */
    @Test
    public void finishPasswordResetUsingPOSTTest() {
        KeyAndPasswordVM keyAndPassword = null;
        // String response = api.finishPasswordResetUsingPOST(keyAndPassword);

        // TODO: test validations
    }
    
    /**
     * 获取当前账户信息
     *
     * 获取当前账户信息
     */
    @Test
    public void getAccountUsingGETTest() {
        // UserDTO response = api.getAccountUsingGET();

        // TODO: test validations
    }
    
    /**
     * isAuthenticated
     *
     * 
     */
    @Test
    public void isAuthenticatedUsingGETTest() {
        // String response = api.isAuthenticatedUsingGET();

        // TODO: test validations
    }
    
    /**
     * registerAccount
     *
     * 
     */
    @Test
    public void registerAccountUsingPOSTTest() {
        ManagedUserVM managedUserVM = null;
        // ResponseEntity response = api.registerAccountUsingPOST(managedUserVM);

        // TODO: test validations
    }
    
    /**
     * requestPasswordReset
     *
     * 
     */
    @Test
    public void requestPasswordResetUsingPOSTTest() {
        String mail = null;
        // ResponseEntity response = api.requestPasswordResetUsingPOST(mail);

        // TODO: test validations
    }
    
    /**
     * saveAccount
     *
     * 
     */
    @Test
    public void saveAccountUsingPOSTTest() {
        UserDTO userDTO = null;
        // ResponseEntity response = api.saveAccountUsingPOST(userDTO);

        // TODO: test validations
    }
    
}
