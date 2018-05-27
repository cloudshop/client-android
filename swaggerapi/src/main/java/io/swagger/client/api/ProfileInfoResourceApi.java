package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;



import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import io.swagger.client.model.ProfileInfoVM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ProfileInfoResourceApi {
  /**
   * getActiveProfiles
   * 
   * @return Call&lt;ProfileInfoVM&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("api/profile-info")
  Call<ProfileInfoVM> getActiveProfilesUsingGET();
    

}
