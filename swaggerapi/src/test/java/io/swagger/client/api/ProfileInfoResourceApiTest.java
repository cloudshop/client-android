package io.swagger.client.api;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ProfileInfoVM;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ProfileInfoResourceApi
 */
public class ProfileInfoResourceApiTest {

    private ProfileInfoResourceApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(ProfileInfoResourceApi.class);
    }

    
    /**
     * getActiveProfiles
     *
     * 
     */
    @Test
    public void getActiveProfilesUsingGETTest() {
        // ProfileInfoVM response = api.getActiveProfilesUsingGET();

        // TODO: test validations
    }
    
}
