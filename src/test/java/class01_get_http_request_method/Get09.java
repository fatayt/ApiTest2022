package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/74
        When
            Url'e GET Request gonder
        Then
            Response body asagidaki gibi olmali;
            {
                "firstname": "James",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates":
                        {
                        "checkin": "2013-02-23",
                        "checkout": "2014-10-23"
                         }
                "additionalneeds": "Breakfast"
            }
     */
    @Test
    public void get09(){
        //1. url'e set et
        spec.pathParams("first", "booking", "second", 74);

        //2. Expected datayi set et
        Map<String, String> expectedBookingDates = new HashMap<>();
        expectedBookingDates.put("checkin", "2018-01-01");
        expectedBookingDates.put("checkout", "2019-01-01");

        Map<String, Object> expectedDate = new HashMap<>();
        expectedDate.put("firstname", "Dane");
        expectedDate.put("lastname", "Colque");
        expectedDate.put("totalprice", 111);
        expectedDate.put("depositpaid", true);
        expectedDate.put("bookingdates", expectedBookingDates);
        expectedDate.put("additionalneeds", "Breakfast");

        System.out.println(expectedDate);

        //3. Request gonder ve Response al
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4. Assertion yap
        assertEquals(expectedDate.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedDate.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedDate.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedDate.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedDate.get("additionalneeds"), actualData.get("additionalneeds"));

        assertEquals(expectedBookingDates.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingDates.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));

    }




}
