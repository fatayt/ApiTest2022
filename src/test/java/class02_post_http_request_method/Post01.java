package class02_post_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class Post01 extends HerOkuAppBaseUrl {
    /*
Given
 https://restful-booker.herokuapp.com/booking
{
    "firstname": "Eyup",
    "lastname": "Yilmaz",
    "totalprice": 2222,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-11-11",
        "checkout": "2022-11-12"
    }
}
When
     URL'e POST Request gonder
Then
    Status code is 200
    And response body asagidaki olmali
            {
          "bookingid": 11,
          "booking": {
                    "firstname": "Selim",
                    "lastname": "Ak",
                    "totalprice": 11111,
                    "depositpaid": true,
                    "bookingdates": {
                             "checkin": "2021-09-09",
                             "checkout": "2021-09-21"
                                 }
                     }
               }
 */
@Test
    public void post01(){
    // 1.adim: url`yi set et
    spec.pathParam("first", "booking");

    // 2.adim: expected data set et
    Map<String, String> expectedBookingDates = new HashMap<>();
    expectedBookingDates.put("checkin", "2022-11-11");
    expectedBookingDates.put("checkout", "2022-11-12");
    System.out.println(expectedBookingDates);

    Map<String, Object> expectedDate = new HashMap<>();
    expectedDate.put("firstname", "Eyup");
    expectedDate.put("lastname", "Yilmaz");
    expectedDate.put("totalprice", 2222);
    expectedDate.put("depositpaid", true);
    expectedDate.put("bookingdates", expectedBookingDates);
    //expectedData.put("additionalneeds", "Breakfast");

    System.out.println(expectedDate);

    //3.adim: Post request ve response al
    Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDate).when().post("/{first}");
    response.prettyPrint();

    //4.assertion yap
    response.then().assertThat().statusCode(200);

    Map<String, Object> actualData = response.as(HashMap.class);
    System.out.println(actualData);

    assertEquals(expectedDate.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
    assertEquals(expectedDate.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
    assertEquals(expectedDate.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
    assertEquals(expectedDate.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));

    assertEquals(expectedBookingDates.get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
    assertEquals(expectedBookingDates.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

    }
}
