package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;



import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import io.swagger.client.model.KeyAndPasswordVM;
import io.swagger.client.model.ManagedUserVM;
import io.swagger.client.model.ResponseEntity;
import io.swagger.client.model.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface AccountResourceApi {
  /**
   * activateAccount
   * 
   * @param key key (required)
   * @return Call&lt;String&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("api/activate")
  Call<String> activateAccountUsingGET(
    @retrofit2.http.Query("key") String key
  );

  /**
   * changePassword
   * 
   * @param password password (required)
   * @return Call&lt;ResponseEntity&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/account/change_password")
  Call<ResponseEntity> changePasswordUsingPOST(
    @retrofit2.http.Body String password
  );

  /**
   * finishPasswordReset
   * 
   * @param keyAndPassword keyAndPassword (required)
   * @return Call&lt;String&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/account/reset_password/finish")
  Call<String> finishPasswordResetUsingPOST(
    @retrofit2.http.Body KeyAndPasswordVM keyAndPassword
  );

  /**
   * 获取当前账户信息
   * 获取当前账户信息
   * @return Call&lt;UserDTO&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("api/account")
  Call<UserDTO> getAccountUsingGET();
    

  /**
   * isAuthenticated
   * 
   * @return Call&lt;String&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("api/authenticate")
  Call<String> isAuthenticatedUsingGET();
    

  /**
   * registerAccount
   * 
   * @param managedUserVM managedUserVM (required)
   * @return Call&lt;ResponseEntity&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/register")
  Call<ResponseEntity> registerAccountUsingPOST(
    @retrofit2.http.Body ManagedUserVM managedUserVM
  );

  /**
   * requestPasswordReset
   * 
   * @param mail mail (required)
   * @return Call&lt;ResponseEntity&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/account/reset_password/init")
  Call<ResponseEntity> requestPasswordResetUsingPOST(
    @retrofit2.http.Body String mail
  );

  /**
   * saveAccount
   * 
   * @param userDTO userDTO (required)
   * @return Call&lt;ResponseEntity&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/account")
  Call<ResponseEntity> saveAccountUsingPOST(
    @retrofit2.http.Body UserDTO userDTO
  );

}
