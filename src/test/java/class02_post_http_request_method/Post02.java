package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {
/*
   Given
            https://jsonplaceholder.typicode.com/todos
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
              }
    When
           Url'e POST Request gonder
    Then
        Status code is 201
    And
        response body is like {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
         }
 */
@Test
    public void post02(){
    //1.adim: url`i set et
    spec.pathParam("first", "todos");

    //2.adim expected datayi set et
    Map<String, Object> exceptedData = new HashMap<>();
    exceptedData.put("userId", 55);
    exceptedData.put("title", "Tidy your room");
    exceptedData.put("completed", false);
    System.out.println(exceptedData);

    //3.adim: request gonder ve respond al
    Response response = given().
                                spec(spec).
                                contentType(ContentType.JSON).
                                body(exceptedData).
                                when().
                                post("/{first}");
    response.prettyPrint();

    exceptedData.put("Status Code", 201);

    //4.adim: assertion yap
    Map<String, Object> actualData = response.as(HashMap.class); // De-serialization
    System.out.println(actualData);

    response.then().statusCode(201);

    assertEquals(exceptedData.get("Status Code"), response.getStatusCode());
    assertEquals(exceptedData.get("userId"), actualData.get("userId"));
    assertEquals(exceptedData.get("title"), actualData.get("title"));
    assertEquals(exceptedData.get("completed"), actualData.get("completed"));
}


}
