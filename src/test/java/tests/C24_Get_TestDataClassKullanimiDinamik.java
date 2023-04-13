package tests;

import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C24_Get_TestDataClassKullanimiDinamik extends BaseUrlsonPlaceholder {
    @Test
    public void test01(){
         /*
        https://jsonplaceholder.typicode.com/posts/40 url'ine bir GET request
        yolladigimizda donen response’in status kodunun 200 ve response body’sinin
        asagida verilen ile ayni oldugunu test ediniz.


        Response body :
            {
            "userId": 4,
            "id": 40,
            "title": "enim quo cumque",
            "body": "ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam"
            }
         */


        //1- Endpoint ve request body olustur.

        specJsonPlaceholder.pathParams("pp1","posts","pp2","40");




        //2- Expected data olustur.
      JSONObject expectedData=TestDataJsonPlaceholder.JsonBodyOlustur(4,40,"enim quo cumque","ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam");

        System.out.println(expectedData);
        //3-Request gönder ve dönen response kaydet.
        Response response=given().spec(specJsonPlaceholder)
                          .when()
                           .get("/{pp1}/{pp2}");

        response.prettyPrint();


        //4-Assertion
       JsonPath responseJsonPath=response.jsonPath();

        assertEquals(TestDataJsonPlaceholder.basariliSorgustatusCode,response.statusCode());
        assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));





    }
}
