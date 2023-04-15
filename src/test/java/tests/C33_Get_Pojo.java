package tests;

import baseUrl.BaseUrlDummyExamble;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummyExampleData;
import pojos.PojoDummyExampleResponse;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C33_Get_Pojo extends BaseUrlDummyExamble {
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

        //2- Soruda varsa Expected data olustur.
        PojoDummyExampleData dataPojo=new PojoDummyExampleData(3,"Ashton Cox",86000,66,"");

        PojoDummyExampleResponse expectedResponseBody=new PojoDummyExampleResponse("success",dataPojo,"Successfully! Record has been fetched.");

        // System.out.println();
        //3-Request gönder ve dönen response kaydet.

        Response response=given().spec(specDummyExamble)
                         .when()
                         .get("{pp1}/{pp2}");
       response.prettyPrint();
        System.out.println(expectedResponseBody);
        //4-Assertion
        //Assertion yapabilmemiz icin reponse pojoya cevirmemiz gerek .(De-Seralization)
        // System.out.println(responsePojo);

        /*
        pojos.PojoDummyExampleResponse@2c0b4c83[
        status=success,
        data=pojos.PojoDummyExampleData@78525ef9[id=3,
        employeeName=Ashton Cox,
        employeeSalary=86000,
        employeeAge=66,
        profileImage=],
        message=Successfully! Record has been fetched.]

         */

       // expectedResponseBody(Pojo)<======================> ResponseJSonPath

        JsonPath responseJp=response.jsonPath();

            assertEquals(expectedResponseBody.getStatus(),responseJp.getString("status"));
            assertEquals(expectedResponseBody.getMessage(),responseJp.getString("message"));
            assertEquals(expectedResponseBody.getData().getId(),responseJp.get("data.id"));
            assertEquals(expectedResponseBody.getData().getEmployeeName(),responseJp.get("data.employee_name"));
            assertEquals(expectedResponseBody.getData().getEmployeeSalary(),responseJp.get("data.employee_salary"));
            assertEquals(expectedResponseBody.getData().getEmployeeAge(),responseJp.get("data.employee_age"));
            assertEquals(expectedResponseBody.getData().getProfileImage(),responseJp.get("data.profile_image"));
    }
}
