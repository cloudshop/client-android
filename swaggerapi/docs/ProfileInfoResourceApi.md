# ProfileInfoResourceApi

All URIs are relative to *https://139.129.39.128:9999*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getActiveProfilesUsingGET**](ProfileInfoResourceApi.md#getActiveProfilesUsingGET) | **GET** api/profile-info | getActiveProfiles


<a name="getActiveProfilesUsingGET"></a>
# **getActiveProfilesUsingGET**
> ProfileInfoVM getActiveProfilesUsingGET()

getActiveProfiles

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProfileInfoResourceApi;


ProfileInfoResourceApi apiInstance = new ProfileInfoResourceApi();
try {
    ProfileInfoVM result = apiInstance.getActiveProfilesUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProfileInfoResourceApi#getActiveProfilesUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ProfileInfoVM**](ProfileInfoVM.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

