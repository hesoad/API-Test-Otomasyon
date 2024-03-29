package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C16_Put_SoftAssertIleExpectedDataTest {

    @Test
    public void put01(){
        /*
        http://dummy.restapiexample.com/api/v1/update/21 url’ine asagidaki body’ye sahip bir PUT
        request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

        {
               "status": "success",
                "data": {
                "name": “Ahmet",
                "salary": "1230",
                "age": "44",
                "id": 40
                }
          }


                Response Body
                { "status": "success",
                   "data": {
                    "status": "success",
                     "data": {
                     "name": “Ahmet",
                    "salary": "1230",
                     "age": "44",
                    "id": 40 }
                },
                "message": "Successfully! Record has been updated."}
                }

         */
        //1- Endpoint ve request body olustur
        String url="https://dummy.restapiexample.com/api/v1/update/21";

        JSONObject requestBody= new JSONObject();
        JSONObject dataBilgileriJson=new JSONObject();

        dataBilgileriJson.put("name","Ahmet");
        dataBilgileriJson.put("salary","1230");
        dataBilgileriJson.put("age","44");
        dataBilgileriJson.put("id",40);

        requestBody.put( "status","success");
        requestBody.put("data",dataBilgileriJson);
        System.out.println(requestBody.toString());

        //2-Expected data olustur.
        JSONObject expectedData =new JSONObject();
        expectedData.put("status","success");
        expectedData.put("data",requestBody);
        expectedData.put("message","Successfully! Record has been updated.");


        System.out.println(expectedData.toString());

        //3-Requesti gonder ve donen response kaydet

        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .put(url);

        //4-Assertion
        response.prettyPrint();

        JsonPath responseJsonpath= response.jsonPath();

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(responseJsonpath.get("status"),expectedData.get("status"));
        softAssert.assertEquals(responseJsonpath.get("message"),expectedData.get("message"));
        softAssert.assertEquals(responseJsonpath.get("data.status"),expectedData.getJSONObject("data").get("status"));
        softAssert.assertEquals(responseJsonpath.get("data.data.name"),expectedData.getJSONObject("data")
                .getJSONObject("data").get("name"));
        softAssert.assertEquals(responseJsonpath.get("data.data.id"),expectedData.getJSONObject("data")
                .getJSONObject("data").get("id"));
        softAssert.assertEquals(responseJsonpath.get("data.data.salary"),expectedData.getJSONObject("data")
                .getJSONObject("data").get("salary"));
        softAssert.assertEquals(responseJsonpath.get("data.data.age"),expectedData.getJSONObject("data")
                .getJSONObject("data").get("age"));

        softAssert.assertAll();




    }
}
