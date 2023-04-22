package com.envision.tests;

 import com.envision.core.DataProviderArgs;
 import com.envision.core.DataProviderUtils;
 import com.envision.core.RestAssuredActions;
 import com.envision.pojo.Job;
 import com.envision.utils.ApiUtils;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import io.restassured.http.Method;
 import io.restassured.response.Response;
 import io.restassured.response.ValidatableResponse;
 import org.json.JSONObject;
 import org.junit.Assert;
 import org.testng.annotations.Test;

 import java.io.IOException;

 import static io.restassured.RestAssured.*;
 import static  io.restassured.matcher.RestAssuredMatchers.*;
 import static org.hamcrest.Matchers.*;

public class FirstRestAssuredTest {
    String baseURI = "https://reqres.in/";
    String endpoint = "/api/users?page=2";
    int expectedResponseCode = 200;


    @Test
    public void testListUsersAPIWithTestNGAssertions(){
      Response response = given().log().all()
                .baseUri(baseURI)
                .basePath(endpoint)
                .when()
                .request(Method.GET);
        response.then().log().all();
        Assert.assertEquals(expectedResponseCode,response.getStatusCode());
        Assert.assertTrue(response.getBody().asPrettyString().contains("tracey.ramos@reqres.in"));
    }

    @Test
    public void testListUsersAPIWithoutTestNGAssertions(){
        given().log().all()
                .baseUri(baseURI)
                .basePath(endpoint)
                .when()
                .request(Method.GET)
                .then()
                .log()
                .all()
                .and().assertThat().statusCode(200)
                .and().assertThat().body(containsString("tracey.ramos@reqres.in"))
                .and().assertThat().contentType("application/json; charset=utf-8")
                .and().assertThat().body("total_pages",equalTo(2))
                .and().assertThat().body("data[0].email", equalTo("george.bluth@reqres.in"));

    }

    @DataProviderArgs(value="createUser=baseUri,endPoint,payload,statusCode,method,name,job")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testUserCreationPostAPI(String baseUri, String endPoint,String payload, String statusCode,String method, String name, String job) throws IOException {
        String jsonBody = ApiUtils.getStringBody(System.getProperty("user.dir")
                +payload);
        jsonBody = jsonBody.replaceAll("%name%",name);
        jsonBody = jsonBody.replaceAll("%job%",job);
        Response response =RestAssuredActions.doPostRequest(baseUri,endPoint,method,jsonBody);
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body(containsString("createdAt"));

    }

    @DataProviderArgs(value="createUser=baseUri,endPoint,payload,statusCode,method,name,job")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testUserCreationPostAPIPojo(String baseUri, String endPoint,String payload, String statusCode,String method, String name, String job) throws IOException {

        Job jobs = new Job();
        jobs.setJob("zion resident");
        jobs.setName("Tarun Sachdeva");
        JSONObject jsonBody = new JSONObject(jobs);
        System.out.println(jsonBody);
        Response response =RestAssuredActions.doPostRequest(baseUri,endPoint,method,jsonBody.toString());
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body(containsString("createdAt"));


    }



}
