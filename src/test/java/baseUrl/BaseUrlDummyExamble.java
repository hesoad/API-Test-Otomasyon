package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlDummyExamble {


   protected RequestSpecification specDummyExamble;

   @Before
    public void setUp(){

       specDummyExamble=new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/api/v1").build();

   }
}
