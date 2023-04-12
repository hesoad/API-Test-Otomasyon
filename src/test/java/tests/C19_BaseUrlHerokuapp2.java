package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C19_BaseUrlHerokuapp2 extends BaseUrlHerokuapp {

    @Test
    public void test01(){
        //2- https://restful-booker.herokuapp.com/booking
        //endpointine asagidaki body’ye sahip bir POST
        //request gonderdigimizde donen response’un
        //status code’unun 200 oldugunu ve “firstname”
        //degerinin “Ahmet” oldugunu test edin

        /*
            {
            "firstname" : "Ahmet",
            "lastname" : “Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
            "checkin" : "2021-06-01",
            "checkout" : "2021-06-10"
            },
            "additionalneeds" : "wi-fi"
            }
                     */
        //1- Endpoint ve request body olustur
        specHerokuapp.pathParam("pp1","booking");

        JSONObject requestBody=new JSONObject();
        JSONObject rezervasyonTraihleri=new JSONObject();

        rezervasyonTraihleri.put( "checkin","2021-06-01");
        rezervasyonTraihleri.put( "checkout","2021-06-10");

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname" ,"Bulut");
        requestBody.put( "totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates",rezervasyonTraihleri);
        requestBody.put("additionalneeds","wi-fi");

        //2-Expected data olustur.
        //3-Requesti gonder ve donen response kaydet.
        Response response=given().contentType(ContentType.JSON)
                .when().spec(specHerokuapp).body(requestBody.toString())
                .post("/{pp1}");

       // response.prettyPrint();
        //4-Assertion


        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname", Matchers.equalTo("Ahmet"));
    }
}
