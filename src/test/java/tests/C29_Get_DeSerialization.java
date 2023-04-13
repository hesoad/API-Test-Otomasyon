package tests;

import TestDatalari.TestDataDummyExamble;

import baseUrl.BaseUrlDummyExamble;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C29_Get_DeSerialization extends BaseUrlDummyExamble {


    @Test
    public void test01(){

        /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.


        Response Body
        {
        "status": "success",
        "data": {
                "id": 3,
                "employee_name": "Ashton Cox",
                "employee_salary": 86000,
                "employee_age": 66,
                "profile_image": ""
                },
        "message": "Successfully! Record has been fetched."
        }



         */


        //1- Endpoint ve request body olustur.

        specDummyExamble.pathParams("pp1","employee","pp2",3);

        //Request body'sini Map olarak olusturalim.


        //2- Expected data olustur.
        Map<String,Object>expectedDataMap= TestDataDummyExamble.mapBodyOlustur();





        // System.out.println(expectedDataMap);


        //3-Request gönder ve dönen response kaydet.
        Response response=given().spec(specDummyExamble).contentType(ContentType.JSON)
                            .when()
                            .get("{pp1}/{pp2}");


        //response.prettyPrint();

        //4-Assertion


        //Assertion yapabilmemiz icin reponse i map e cevrimemeiz gerek .(De-Seralization)

        Map<String,Object>responseMap= response.as(HashMap.class);


      System.out.println(responseMap);

        //expectedData (Map)<====> responseMap(Map)
      assertEquals(TestDataDummyExamble.BasariliSorguCode,response.statusCode());
      assertEquals(TestDataDummyExamble.contentType,response.contentType());
      assertEquals(expectedDataMap.get("message"),responseMap.get("message"));
      assertEquals(expectedDataMap.get("status"),responseMap.get("status"));
      assertEquals(((Map)expectedDataMap.get("data")).get("profile_image"),  ((Map)responseMap.get("data")).get("profile_image"));
      assertEquals(((Map)expectedDataMap.get("data")).get("employee_name"),  ((Map)responseMap.get("data")).get("employee_name"));
      assertEquals(((Map)expectedDataMap.get("data")).get("employee_salary"), ((Map)responseMap.get("data")).get("employee_salary"));
      assertEquals(((Map)expectedDataMap.get("data")).get("employee_age"),   ((Map)responseMap.get("data")).get("employee_age"));



    }
}
