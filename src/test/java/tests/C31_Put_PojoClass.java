package tests;

import TestDatalari.TestDataHerokuapp;
import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoJsonPlaceholder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C31_Put_PojoClass extends BaseUrlsonPlaceholder {

    @Test
    public void test01(){
        /*
                https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
                PUT request yolladigimizda donen response’in response body’sinin asagida
                verilen ile ayni oldugunu test ediniz

                POJO CLASS ILE EXPECTED DATA TESTI
                Expected Data :
                {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
                }
                Request Body
                {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
                }

         */
        //1- Endpoint ve request body olustur.
        specJsonPlaceholder.pathParams("pp1","posts","pp2",70);

        PojoJsonPlaceholder requestBodyPojo=new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);





        //2- Soruda varsa Expected data olustur.

        PojoJsonPlaceholder expecteddataPojo=new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);



     // System.out.println(expecteddataPojo);

        //3-Request gönder ve dönen response kaydet.

        Response response= given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                            .when().body(requestBodyPojo)
                            .put("{pp1}/{pp2}");



       //response.prettyPrint();
        //4-Assertion
        //Assertion yapabilmemiz icin reponse pojoya cevirmemiz gerek .(De-Seralization)
        PojoJsonPlaceholder responsePojo=response.as(PojoJsonPlaceholder.class);

       // System.out.println(responsePojo);


        //status kodunun 200,
     assertEquals(TestDataJsonPlaceholder.basariliSorgustatusCode,response.statusCode());
     //content Type nin ....
     assertEquals(TestDataJsonPlaceholder.contentType,response.contentType());
     //connection header degerinin"..." oldugunu
     assertEquals(TestDataJsonPlaceholder.headerConnection,response.header("Connection"));

     //response body’sinin asagida verilen ile ayni oldugunu test ediniz

        assertEquals(expecteddataPojo.getTitle(),responsePojo.getTitle());
        assertEquals(expecteddataPojo.getBody(),responsePojo.getBody());
        assertEquals(expecteddataPojo.getId(),responsePojo.getId());
        assertEquals(expecteddataPojo.getUserId(),responsePojo.getUserId());




    }
}
