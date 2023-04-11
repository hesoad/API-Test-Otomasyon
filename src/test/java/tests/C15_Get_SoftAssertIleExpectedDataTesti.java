package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C15_Get_SoftAssertIleExpectedDataTesti {


    @Test
    public void get01(){

        /* http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
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

       //1- Endpoint ve request body olustur

        String url=" http://dummy.restapiexample.com/api/v1/employee/3";

        //2-expected data olustur.

        JSONObject expectedData= new JSONObject();
        JSONObject dataBilgilerijsonObject=new JSONObject();

        dataBilgilerijsonObject.put("id",3);
        dataBilgilerijsonObject.put("employee_name","Ashton Cox");
        dataBilgilerijsonObject.put("employee_salary",86000);
        dataBilgilerijsonObject.put("employee_age",66);
        dataBilgilerijsonObject.put("profile_image", "");


        expectedData.put("status","success");
        expectedData.put("data",dataBilgilerijsonObject);
        expectedData.put( "message","Successfully! Record has been fetched.");

        System.out.println(expectedData.toString());

        //request gonderip, donen sonucu kaydet.
        Response response=given().when().get(url);


        //4-Assertion
        //oncelikle response dan gelen bilgilere kolay ulasmak icin, objeyi JsonPath'e donusturmek lazim.

        JsonPath responseJsonPath=response.jsonPath();

        // Assertion lari soft assert ile yapalim

        SoftAssert softAssert=new SoftAssert();


        softAssert.assertEquals(responseJsonPath.get("status"),expectedData.get("status"));
        softAssert.assertEquals(responseJsonPath.get("message"),expectedData.get("message"));
        softAssert.assertEquals(responseJsonPath.get("data.id"),expectedData.getJSONObject("data").get("id"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_age"),expectedData.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_salary"),expectedData.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_name"),expectedData.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(responseJsonPath.get("data.profile_image"),expectedData.getJSONObject("data").get("profile_image"));



        softAssert.assertAll();




    }
}
