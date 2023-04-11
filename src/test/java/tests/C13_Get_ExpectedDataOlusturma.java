package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C13_Get_ExpectedDataOlusturma {

    @Test
    public void test01(){
        /*
             https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request
            yolladigimizda donen response body’sinin asagida verilen ile ayni oldugunu test
            ediniz
            Response body :
            {
            "userId": 3,
            "id": 22,
            "title": "dolor sint quo a velit explicabo quia nam",
            "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
            um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
            }
         */

        //1-end point ve request body hazirla
        String url ="https://jsonplaceholder.typicode.com/posts/22";

        //2- expected data olustur


        JSONObject expectedData=new JSONObject();
        expectedData.put("userId",3);
        expectedData.put("id",22);
        expectedData.put("title", "dolor sint quo a velit explicabo quia nam");
        expectedData.put("body", "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear"+
        "um mollitia molestiae aut atque rem suscipit\nnam impedit esse");
        System.out.println(expectedData.toString());

        //3- request gonder ve donen response kaydet.

        Response response=given().when().get(url);

        //4-Assertion
       /* response.then().assertThat().body("userId",equalTo(3),
        "id",equalTo(22),
             "title",equalTo("dolor sint quo a velit explicabo quia nam"));

        */

        JsonPath reponseJsonPath= response.jsonPath();

        Assert.assertEquals(expectedData.get("id"),reponseJsonPath.get("id"));
        Assert.assertEquals(expectedData.get("title"),reponseJsonPath.get("title"));
        Assert.assertEquals(expectedData.get("body"),reponseJsonPath.get("body"));
        Assert.assertEquals(expectedData.get("userId"),reponseJsonPath.get("userId"));

    }
}
