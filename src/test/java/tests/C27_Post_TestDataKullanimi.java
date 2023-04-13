package tests;

import TestDatalari.TestDataDummyExamble;
import TestDatalari.TestDataHerokuapp;
import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C27_Post_TestDataKullanimi extends BaseUrlHerokuapp {

    @Test
    public void test01(){
        /*
                https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
                request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

                Request body
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



                Response Body
                {
                "bookingid": 24,
                "booking": {
                "firstname": "Ahmet",
                "lastname": "Bulut",
                "totalprice": 500,
                "depositpaid": false,
                "bookingdates": {
                "checkin": "2021-06-01",
                "checkout": "2021-06-10"
                },
                "additionalneeds": "wi-fi"
                }
                }
         */

        //1- Endpoint ve request body olustur.

        specHerokuapp.pathParam("pp1","booking");
        JSONObject requestBody= TestDataHerokuapp.jsonRequestBodyOlustur();




        //2- Expected data olustur.


        JSONObject expectedData=TestDataHerokuapp.jsonResponseBodyOlustur();

       // System.out.println(expectedData);


        //3-Request gönder ve dönen response kaydet.

        Response response=   given().spec(specHerokuapp).contentType(ContentType.JSON)
                             .when().body(requestBody.toString())
                             .post("{pp1}");


       // response.prettyPrint();

        //4-Assertion
        JsonPath responseJp=response.jsonPath();


        assertEquals(expectedData.getJSONObject("booking").getString("firstname"),responseJp.getString("booking.firstname"));
        assertEquals(expectedData.getJSONObject("booking").getString("lastname"),responseJp.getString("booking.lastname"));
        assertEquals(expectedData.getJSONObject("booking").getInt("totalprice"),responseJp.getInt("booking.totalprice"));
        assertEquals(expectedData.getJSONObject("booking").getBoolean("depositpaid"),responseJp.getBoolean("booking.depositpaid"));
        assertEquals(expectedData.getJSONObject("booking").getString("additionalneeds"),responseJp.getString("booking.additionalneeds"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"),responseJp.getString("booking.bookingdates.checkin"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"),responseJp.getString("booking.bookingdates.checkout"));







    }
}
