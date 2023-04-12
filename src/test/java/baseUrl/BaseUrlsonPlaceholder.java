package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlsonPlaceholder{

    protected RequestSpecification specJsonPlaceholder;

    @Before
    public void setUp01(){

        specJsonPlaceholder=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }



}
