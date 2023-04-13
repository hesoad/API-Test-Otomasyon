package tests;

import TestDatalari.TestDataHerokuapp;
import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C28_Put_DeSerialization extends BaseUrlsonPlaceholder {

    @Test
    public void test01(){
        /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
          PUT request yolladigimizda donen response’in response body’sinin asagida
          verilen ile ayni oldugunu test ediniz.

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

        //Request body'sini Map olarak olusturalim.

        Map<String,Object> requestBodyMap= TestDataJsonPlaceholder.mapBodyOlustur();

        System.out.println(requestBodyMap);


        //2- Expected data olustur.
        Map<String,Object>expectedData=TestDataJsonPlaceholder.mapBodyOlustur();

        // System.out.println(expectedData);


        //3-Request gönder ve dönen response kaydet.

        Response response =given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                             .when().body(requestBodyMap)
                             .put("{pp1}/{pp2}");

         //response.prettyPrint();

        //4-Assertion


        //Assertion yapabilmemiz icin reponse i map e cevrimemeiz gerek .(De-Seralization)

        Map<String,Object>responseMap=response.as(HashMap.class);

       // System.out.println(responseMap);

        //expectedData (Map)<====> responseMap(Map)

        assertEquals(expectedData.get("title"),responseMap.get("title"));
        assertEquals(expectedData.get("body"),responseMap.get("body"));
        assertEquals(expectedData.get("id"),responseMap.get("id"));
        assertEquals(expectedData.get("userId"),responseMap.get("userId"));

    }
}
