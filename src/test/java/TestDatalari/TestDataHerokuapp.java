package TestDatalari;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    /*
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
     */
    public static Map<String,Object>mapRequestBodyOlustur(){
        Map<String,Object>requestBodymap=new HashMap<>();

        requestBodymap.put("firstname" , "Ahmet");
        requestBodymap.put("lastname" , "Bulut");
        requestBodymap.put("totalprice" , 500.0);
        requestBodymap.put("depositpaid" , false);
        requestBodymap.put("additionalneeds" ,"wi-fi");
        requestBodymap.put("bookingdates",mapBookingDatesOlustur());

return requestBodymap;
    }

    public static Map<String,String> mapBookingDatesOlustur(){

        Map<String,String>bookingDatesMap=new HashMap<>();

        bookingDatesMap.put("checkin" , "2021-06-01");
        bookingDatesMap.put("checkout","2021-06-10");

   return bookingDatesMap;
    }

    /*
     Response Body // expected data
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
    public static Map<String,Object>responseBodyMapOlustur(){
        Map<String,Object>responseBody=new HashMap<>();
        responseBody.put("bookingid", 24);
        responseBody.put("booking", mapRequestBodyOlustur());

        return  responseBody;

    }
}
