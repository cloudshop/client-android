# AccountResourceApi

All URIs are relative to *https://139.129.39.128:9999*

Method | HTTP request | Description
------------- | ------------- | -------------
[**activateAccountUsingGET**](AccountResourceApi.md#activateAccountUsingGET) | **GET** api/activate | activateAccount
[**changePasswordUsingPOST**](AccountResourceApi.md#changePasswordUsingPOST) | **POST** api/account/change_password | changePassword
[**finishPasswordResetUsingPOST**](AccountResourceApi.md#finishPasswordResetUsingPOST) | **POST** api/account/reset_password/finish | finishPasswordReset
[**getAccountUsingGET**](AccountResourceApi.md#getAccountUsingGET) | **GET** api/account | 获取当前账户信息
[**isAuthenticatedUsingGET**](AccountResourceApi.md#isAuthenticatedUsingGET) | **GET** api/authenticate | isAuthenticated
[**registerAccountUsingPOST**](AccountResourceApi.md#registerAccountUsingPOST) | **POST** api/register | registerAccount
[**requestPasswordResetUsingPOST**](AccountResourceApi.md#requestPasswordResetUsingPOST) | **POST** api/account/reset_password/init | requestPasswordReset
[**saveAccountUsingPOST**](AccountResourceApi.md#saveAccountUsingPOST) | **POST** api/account | saveAccount


<a name="activateAccountUsingGET"></a>
# **activateAccountUsingGET**
> String activateAccountUsingGET(key)

activateAccount

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
String key = "key_example"; // String | key
try {
    String result = apiInstance.activateAccountUsingGET(key);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#activateAccountUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **key** | **String**| key |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json;charset=UTF-8

<a name="changePasswordUsingPOST"></a>
# **changePasswordUsingPOST**
> ResponseEntity changePasswordUsingPOST(password)

changePassword

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
String password = "password_example"; // String | password
try {
    ResponseEntity result = apiInstance.changePasswordUsingPOST(password);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#changePasswordUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **password** | **String**| password |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: text/plain

<a name="finishPasswordResetUsingPOST"></a>
# **finishPasswordResetUsingPOST**
> String finishPasswordResetUsingPOST(keyAndPassword)

finishPasswordReset

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
KeyAndPasswordVM keyAndPassword = new KeyAndPasswordVM(); // KeyAndPasswordVM | keyAndPassword
try {
    String result = apiInstance.finishPasswordResetUsingPOST(keyAndPassword);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#finishPasswordResetUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **keyAndPassword** | [**KeyAndPasswordVM**](KeyAndPasswordVM.md)| keyAndPassword |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: text/plain

<a name="getAccountUsingGET"></a>
# **getAccountUsingGET**
> UserDTO getAccountUsingGET()

获取当前账户信息

获取当前账户信息

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AccountResourceApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: password_token
OAuth password_token = (OAuth) defaultClient.getAuthentication("password_token");
password_token.setAccessToken("YOUR ACCESS TOKEN");

AccountResourceApi apiInstance = new AccountResourceApi();
try {
    UserDTO result = apiInstance.getAccountUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#getAccountUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**UserDTO**](UserDTO.md)

### Authorization

[password_token](../README.md#password_token)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json;charset=UTF-8

<a name="isAuthenticatedUsingGET"></a>
# **isAuthenticatedUsingGET**
> String isAuthenticatedUsingGET()

isAuthenticated

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
try {
    String result = apiInstance.isAuthenticatedUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#isAuthenticatedUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json;charset=UTF-8

<a name="registerAccountUsingPOST"></a>
# **registerAccountUsingPOST**
> ResponseEntity registerAccountUsingPOST(managedUserVM)

registerAccount

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
ManagedUserVM managedUserVM = new ManagedUserVM(); // ManagedUserVM | managedUserVM
try {
    ResponseEntity result = apiInstance.registerAccountUsingPOST(managedUserVM);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#registerAccountUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **managedUserVM** | [**ManagedUserVM**](ManagedUserVM.md)| managedUserVM |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain

<a name="requestPasswordResetUsingPOST"></a>
# **requestPasswordResetUsingPOST**
> ResponseEntity requestPasswordResetUsingPOST(mail)

requestPasswordReset

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
String mail = "mail_example"; // String | mail
try {
    ResponseEntity result = apiInstance.requestPasswordResetUsingPOST(mail);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#requestPasswordResetUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **mail** | **String**| mail |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: text/plain

<a name="saveAccountUsingPOST"></a>
# **saveAccountUsingPOST**
> ResponseEntity saveAccountUsingPOST(userDTO)

saveAccount

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AccountResourceApi;


AccountResourceApi apiInstance = new AccountResourceApi();
UserDTO userDTO = new UserDTO(); // UserDTO | userDTO
try {
    ResponseEntity result = apiInstance.saveAccountUsingPOST(userDTO);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountResourceApi#saveAccountUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userDTO** | [**UserDTO**](UserDTO.md)| userDTO |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json;charset=UTF-8

