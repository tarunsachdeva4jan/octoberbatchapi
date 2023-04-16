package com.envision.core;

import com.envision.utils.ApiUtils;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;


import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredActions {

    public static Response doGetRequest(String baseURI, String endPoint, String methodType){
       Response response = given()
                .log()
                .all()
                .baseUri(baseURI)
                .basePath(endPoint)
                .when()
                .request(ApiUtils.getMethodType(methodType));

       return response;
    }

    public static Response doPostRequest(String baseURI, String endPoint, String methodType, String payload){
        Response response = given()
                .log()
                .all()
                .baseUri(baseURI)
                .basePath(endPoint)
                .body(payload)
                .when()
                .request(ApiUtils.getMethodType(methodType));

        return response;
    }

    public static Response doPostRequest(String baseURI, String endPoint, String username, String password, String methodType, String payload){
        Response response = given()
                .log()
                .all()
                .auth().basic(username,password)
                .baseUri(baseURI)
                .basePath(endPoint)
                .body(payload)
                .when()
                .request(ApiUtils.getMethodType(methodType));

        return response;
    }

    public static Response doPostRequest(String baseURI, String endPoint, String proxyUrl, String username, String password, String methodType, String payload){
        Response response = given()
                .log()
                .all()
                .auth().basic(username,password)
                .baseUri(baseURI)
                .basePath(endPoint)
                .body(payload)
                .when()
                .request(ApiUtils.getMethodType(methodType));

        return response;
    }


    public static Response doPostRequest(String baseURI, String endPoint, String methodType, String payload, Map<String, String> header){
        Response response = given()
                .log()
                .all()
                .baseUri(baseURI)
                .basePath(endPoint)
                .body(payload)
                .headers(header)
                .when()
                .request(ApiUtils.getMethodType(methodType));

        return response;
    }

    public static Response doPostRequest(String baseURI, String endPoint, String methodType, String payload, Map<String, String> header, String cookieValue
    ){
        Response response = given()
                .log()
                .all()
                .baseUri(baseURI)
                .basePath(endPoint)
                .body(payload)
                .headers(header)
                .cookie(cookieValue)
                .when()
                .request(ApiUtils.getMethodType(methodType));

        return response;
    }



}
