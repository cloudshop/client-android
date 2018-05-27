package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;



import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import io.swagger.client.model.ManagedUserVM;
import io.swagger.client.model.ResponseEntity;
import io.swagger.client.model.User;
import io.swagger.client.model.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface UserResourceApi {
  /**
   * createUser
   * 
   * @param managedUserVM managedUserVM (required)
   * @return Call&lt;ResponseEntity&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/users")
  Call<ResponseEntity> createUserUsingPOST(
    @retrofit2.http.Body ManagedUserVM managedUserVM
  );

  /**
   * deleteUser
   * 
   * @param login login (required)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @DELETE("api/users/{login}")
  Call<Void> deleteUserUsingDELETE(
    @retrofit2.http.Path("login") String login
  );

  /**
   * getAllUsers
   * 
   * @param page Page number of the requested page (optional)
   * @param size Size of a page (optional)
   * @param sort Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
   * @return Call&lt;List&lt;UserDTO&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("api/users")
  Call<List<UserDTO>> getAllUsersUsingGET(
    @retrofit2.http.Query("page") Integer page, @retrofit2.http.Query("size") Integer size, @retrofit2.http.Query("sort") List<String> sort
  );

  /**
   * getAuthorities
   * 
   * @return Call&lt;List&lt;String&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("api/users/authorities")
  Call<List<String>> getAuthoritiesUsingGET();
    

  /**
   * getUser
   * 
   * @param login login (required)
   * @return Call&lt;UserDTO&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("api/users/{login}")
  Call<UserDTO> getUserUsingGET(
    @retrofit2.http.Path("login") String login
  );

  /**
   * search
   * 
   * @param query query (required)
   * @return Call&lt;List&lt;User&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("api/_search/users/{query}")
  Call<List<User>> searchUsingGET(
    @retrofit2.http.Path("query") String query
  );

  /**
   * updateUser
   * 
   * @param managedUserVM managedUserVM (required)
   * @return Call&lt;UserDTO&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/users")
  Call<UserDTO> updateUserUsingPUT(
    @retrofit2.http.Body ManagedUserVM managedUserVM
  );

}
