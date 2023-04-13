package tests;

import TestDatalari.TestDataDummyExamble;

import baseUrl.BaseUrlDummyExamble;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C26_Get_TestDataKullanimi extends BaseUrlDummyExamble {

    @Test
    public void test01(){

        /*
            https://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
            gonderdigimizde donen response’un status code’unun 200, content Type’inin
            application/json ve body’sinin asagidaki gibi oldugunu test edin.
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
        specDummyExamble.pathParams("pp1","employee","pp2","3");

        //2- Expected data olustur.

        JSONObject expectedData= TestDataDummyExamble.jSonResponseBodyOlustur(3,"Ashton Cox",
                86000,66,"");


        System.out.println(expectedData);





        //3-Request gönder ve dönen response kaydet.

        Response response= given().spec(specDummyExamble)
                           .when()
                           .get("{pp1}/{pp2}");


       response.prettyPrint();


        //4-Assertion
            JsonPath reponseJP=response.jsonPath();


        // status kodunun 200
        assertEquals(TestDataDummyExamble.BasariliSorguCode,response.statusCode());

        //content type’inin
        assertEquals(TestDataDummyExamble.contentType,response.contentType());

        //ve response body’sinin asagida verilen ile ayni oldugunu test ediniz.

        assertEquals(expectedData.getJSONObject("data").getString("profile_image"),reponseJP.getString("data.profile_image"));
        assertEquals(expectedData.getJSONObject("data").getString("employee_name"),reponseJP.getString("data.employee_name"));
        assertEquals(expectedData.getJSONObject("data").getInt("employee_salary"),reponseJP.getInt("data.employee_salary"));
        assertEquals(expectedData.getJSONObject("data").getInt("id"),reponseJP.getInt("data.id"));
        assertEquals(expectedData.getJSONObject("data").getInt("employee_age"),reponseJP.getInt("data.employee_age"));

        assertEquals(expectedData.getString("message"),reponseJP.getString("message"));
        assertEquals(expectedData.getString("status"),reponseJP.getString("status"));



    }
}
