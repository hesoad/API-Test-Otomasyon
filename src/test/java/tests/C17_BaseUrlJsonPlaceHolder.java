package tests;

import baseUrl.BaseUrlsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C17_BaseUrlJsonPlaceHolder extends BaseUrlsonPlaceholder {


    @Test
    public void test01(){
        // 1- https://jsonplaceholder.typicode.com/posts endpointine bir GET request
        //gonderdigimizde donen response’un status code’unun 200 oldugunu ve
        //Response’ta 100 kayit oldugunu test edin

        //1- Endpoint ve request body olustur
        specJsonPlaceholder.pathParam("pp1","posts");

        //2-Expected data olustur.
        //3-Requesti gonder ve donen response kaydet.
        Response response=given()
                      .when().spec(specJsonPlaceholder)
                      .get("/{pp1}");

        //response.prettyPrint();
        //4-Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.hasSize(100));



    }
    @Test
    public void test02(){
        // 2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET request
        // gonderdigimizde donen response’un status code’unun 200 oldugunu ve “title”
        //degerinin “optio dolor molestias sit” oldugunu test edin

        //1-Endpoint ve request body olustur

        specJsonPlaceholder.pathParams("pp1","posts","pp2",44);

        //2-Expected data olustur.
        //3-Requesti gonder ve donen response kaydet.

        Response response=given()
                        .when().spec(specJsonPlaceholder)
                        .get("/{pp1}/{pp2}");

        //response.prettyPrint();

        //4-Assertion
        response
                .then()
                .assertThat().statusCode(200)
                .body("title",Matchers.equalTo("optio dolor molestias sit"));
    }
}
