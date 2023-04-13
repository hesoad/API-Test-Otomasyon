package TestDatalari;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

public class TestDataHerokuapp {


    public static JSONObject jsonRequestBodyOlustur(){


        JSONObject requestBody=new JSONObject();
        JSONObject bookingDatesBody=new JSONObject();


        bookingDatesBody.put("checkin","2021-06-01");
        bookingDatesBody.put("checkout","2021-06-10");


        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname" ,"Bulut");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("additionalneeds","wi-fi");
        requestBody.put("bookingdates" ,bookingDatesBody);

        return requestBody;
    }

    public static JSONObject jsonResponseBodyOlustur(){

        JSONObject responseBody=new JSONObject();
        JSONObject bookingBody=jsonRequestBodyOlustur();

        responseBody.put("bookingid",24);
        responseBody.put("booking",bookingBody);


        return responseBody;
    }
}
