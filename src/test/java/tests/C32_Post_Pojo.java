package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoHerokuappBookinDates;
import pojos.PojoHerokuappRequestBody;
import pojos.PojoHerokuappResponse;
import pojos.PojoJsonPlaceholder;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C32_Post_Pojo extends BaseUrlHerokuapp {

    @Test
    public void test01(){
        //1- Endpoint ve request body olustur.
        //2- Soruda varsa Expected data olustur.
        // System.out.println(expecteddataPojo);
        //3-Request gönder ve dönen response kaydet.
        //response.prettyPrint();
        //4-Assertion
        //Assertion yapabilmemiz icin reponse pojoya cevirmemiz gerek .(De-Seralization)
        // System.out.println(responsePojo);

        /*
        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
                request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.
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
        //1- Endpoint ve request body olustur.
        specHerokuapp.pathParam("pp1","booking");

        PojoHerokuappBookinDates bookinDatesPojo=new PojoHerokuappBookinDates("2021-06-01","2021-06-10");

        PojoHerokuappRequestBody requestBodyPojo=new PojoHerokuappRequestBody("Ahmet","Bulut",
                500,false,bookinDatesPojo,"wi-fi");


        //2- Soruda varsa Expected data olustur.

        bookinDatesPojo=new PojoHerokuappBookinDates("2021-06-01","2021-06-10");

        PojoHerokuappRequestBody bookingPojo=new PojoHerokuappRequestBody("Ahmet","Bulut",
                500,false,bookinDatesPojo,"wi-fi");

        PojoHerokuappResponse expectedResponseBodyPojo=new PojoHerokuappResponse(24,bookingPojo);



       System.out.println(expectedResponseBodyPojo);

        //3-Request gönder ve dönen response kaydet.

        Response response=given().spec(specHerokuapp).contentType(ContentType.JSON)
                        .when().body(requestBodyPojo)
                        .post("{pp1}");



        response.prettyPrint();
        //4-Assertion
        //Assertion yapabilmemiz icin response pojoya cevirmemiz gerek .(De-Seralization)

        PojoHerokuappResponse responsepojo=response.as(PojoHerokuappResponse.class);


       System.out.println(responsepojo);

    assertEquals(expectedResponseBodyPojo.getBooking().getFirstname(),responsepojo.getBooking().getFirstname());
    assertEquals(expectedResponseBodyPojo.getBooking().getLastname(),responsepojo.getBooking().getLastname());
    assertEquals(expectedResponseBodyPojo.getBooking().getTotalprice(),responsepojo.getBooking().getTotalprice());
    assertEquals(expectedResponseBodyPojo.getBooking().isDepositpaid(),responsepojo.getBooking().isDepositpaid());
    assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckin(),
            responsepojo.getBooking().getBookingdates().getCheckin());
    assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckout(),
            responsepojo.getBooking().getBookingdates().getCheckout());
    assertEquals(expectedResponseBodyPojo.getBooking().getAdditionalneeds(),responsepojo.getBooking().getAdditionalneeds());

    }
}
