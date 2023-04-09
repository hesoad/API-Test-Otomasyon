import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_GetRequest_ResponseBodyYazdirma {

    @Test
    public void get01(){
//https://restful-booker.herokuapp.com/booking/10 url sine bir get
// Request gonderdigimizde dönen respons u yazdirin.


        //1-Request body ve end-Point hazirlama
        String url="https://restful-booker.herokuapp.com/booking/10";
        //2-Expected Data hazirlama
        //3-Request gönderip , dönen response kaydetme
        Response response=given().when().get(url);
        response.prettyPrint();
        //4-Assertion


    }
}
