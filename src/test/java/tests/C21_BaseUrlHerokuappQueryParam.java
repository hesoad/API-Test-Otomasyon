package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C21_BaseUrlHerokuappQueryParam extends BaseUrlHerokuapp {

    @Test
    public void  test01(){

        /*
            2- https://restful-booker.herokuapp.com/booking endpointine gerekli Query
            parametrelerini yazarak “firstname” degeri “Eric” olan rezervasyon oldugunu test
            edecek bir GET request gonderdigimizde, donen response’un status code’unun 200
            oldugunu ve “Eric” ismine sahip en az bir booking oldugunu test edin
         */

        //1- Endpoint ve request body olustur.

        specHerokuapp.pathParam("pp1","booking")
                .queryParam("firstname","Eric");

        //2- Expected data olustur.
        //3-Request gönder ve dönen response kaydet.

        Response response=given().contentType(ContentType.JSON)
                .when().spec(specHerokuapp)
                .get("/{pp1}");

       // response.prettyPeek();

        //4-Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasSize(1));
    }


}
