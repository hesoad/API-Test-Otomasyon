import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C11_Post_JsonPathIleBodyTesti {

    @Test
    public void test01(){

        /*
        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
            request gonderdigimizde
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
            POST REQUEST, RESPONSE BODY BILGILERINI
            ASSERT YAPARKEN JSONPATH KULLANMA
            donen Response’un,
            status code’unun 200,
            ve content type’inin application-json,
            ve response body’sindeki
            "firstname“in,"Ahmet",
            ve "lastname“in, "Bulut",
            ve "totalprice“in,500,
            ve "depositpaid“in,false,
            ve "checkin" tarihinin 2021-06-01
            ve "checkout" tarihinin 2021-06-10
            ve "additionalneeds“in,"wi-fi"
            oldugunu test edin
         */

        //1-end point ve request body hazirla
        String url=" https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody=new JSONObject();
        JSONObject rezervasyonTarihleri=new JSONObject();
        rezervasyonTarihleri.put("checkin","2021-06-01");
        rezervasyonTarihleri.put("checkout" , "2021-06-10");

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname" ,"Bulut");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",rezervasyonTarihleri);
        requestBody.put("additionalneeds","wifi");



        //2-expected body olustur
        //3-requesti gonder ve donene response kaydet.

        Response response=given().contentType(ContentType.JSON)
                          .when().body(requestBody.toString())
                          .post(url);
        //response.prettyPrint();

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname",equalTo("Ahmet"))
                .body("booking.lastname",equalTo("Bulut"))
                .body("booking.totalprice",equalTo(500))
                .body("booking.depositpaid",equalTo(false))
                .body("booking.bookingdates.checkin", equalTo("2021-06-01"))
                .body("booking.bookingdates.checkout", equalTo("2021-06-10"));



    }
}
