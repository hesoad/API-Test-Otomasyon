package tests;

import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C25_Put_TestDataClassKullanimi extends BaseUrlsonPlaceholder {

    @Test
    public void test01(){
        /*
            https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
            PUT request yolladigimizda donen response’in
            status kodunun 200, content type’inin “application/json; charset=utf-8”,
            Connection header degerinin “keep-alive”
            ve response body’sinin asagida verilen ile ayni oldugunu test ediniz.


            Request Body
            {
            "title": "Ahmet",
            "body": "Merhaba",
            "userId": 10,
            "id": 70
            }
           Expected Data :
            {
            "title": "Ahmet",
            "body": "Merhaba",
            "userId": 10,
            "id": 70
            }
         */
        //1- Endpoint ve request body olustur.
        specJsonPlaceholder.pathParams("pp1","posts","pp2",70);

        JSONObject requestBody= TestDataJsonPlaceholder.JsonBodyOlustur(10,70,"Ahmet","Merhaba");

        //2- Expected data olustur.
        JSONObject expectedData= TestDataJsonPlaceholder.JsonBodyOlustur(10,70,"Ahmet","Merhaba");
        //3-Request gönder ve dönen response kaydet.

        Response response= given().spec(specJsonPlaceholder)
                          .when().body(requestBody.toString()).contentType(ContentType.JSON)
                          .put("{pp1}/{pp2}");

       // response.prettyPrint();


        //4-Assertion
        JsonPath responseJP=response.jsonPath();
        // status kodunun 200
      assertEquals(TestDataJsonPlaceholder.basariliSorgustatusCode,response.statusCode());

      //content type’inin
      assertEquals(TestDataJsonPlaceholder.contentType,response.contentType());

      //Connection header degerinin
        assertEquals(TestDataJsonPlaceholder.headerConnection,response.header("Connection"));
       //ve response body’sinin asagida verilen ile ayni oldugunu test ediniz.
        assertEquals(expectedData.getInt("id"),responseJP.getInt("id"));
        assertEquals(expectedData.getInt("userId"),responseJP.getInt("userId"));
        assertEquals(expectedData.getString("title"),responseJP.getString("title"));
        assertEquals(expectedData.getString("body"),responseJP.getString("body"));

    }
}
