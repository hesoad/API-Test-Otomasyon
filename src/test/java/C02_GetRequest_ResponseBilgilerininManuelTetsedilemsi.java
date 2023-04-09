import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_GetRequest_ResponseBilgilerininManuelTetsedilemsi {

    @Test
    public void test01(){

        //https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
        //gonderdigimizde donen Response’un,
        //status code’unun 200,
        //ve content type’inin application/json; charset=utf-8,
        //ve Server isimli Header’in degerinin Cowboy,
        //ve status Line’in HTTP/1.1 200 OK
        //ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.

        //1-Gerekli body ve end Point hazirla
        String url ="https://restful-booker.herokuapp.com/booking/10";

        //2-Expected body olustur.
        //3-request gonder ve dönen response kaydet

        Response response=given().when().get(url);
        response.prettyPrint();

        System.out.println("Status code: "+response.getStatusCode()+
                "\ncontent type: "+ response.getContentType()+
                "\nserver Header degeri: "+ response.getHeader("server")+
                "\nstatus Line degeri: "+ response.getStatusLine()+
                "\nresponse suresi: "+ response.getTime()+ " ms");


    }
}
