package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
// import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
   /*
        Given
            https://restful-booker.herokuapp.com/booking/12691
        When
            Kullanici GET request gonderir => URL
        Then
            HTTP Status Code: 200
        And
            Response content type : “application/json”
        And
            Response body asagidaki gibi olmali;
            {
                 "firstname": "Javier",
                 "lastname": "Dominguez",
                 "totalprice": 111,
                 "depositpaid": true,
                 "bookingdates": {
                                  "checkin": "2018-01-01",
                                  "checkout": "2019-01-01"
                                  },
                 "additionalneeds": "Breakfast"
            }
    */


    @Test
    public void get06(){
    //1.adim: url'e set et
        spec.pathParams("first", "booking", "second", 34);

    //2.adim:beklenen datayi set et

    //3.adim: get request gonder ve get response al
    Response response = given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();

    //4. assertion yap
    //1. yol
    response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
            body("firstname", equalTo("Edgar")).
            body("lastname", equalTo("Morante Briones")).
            body("totalprice", equalTo(111)).
            body("depositpaid", equalTo(true)).
            body("bookingdates.checkin", equalTo("2018-01-01")).
            body("bookingdates.checkout",equalTo("2019-01-01")).
            body("additionalneeds", equalTo("Breakfast"));

        //2.yol JsonPath kullanarak assertion yapariz
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();
        assertEquals( "Edgar", json.getString("firstname") );
        assertEquals("Soyisimler eslesmiyor", "Morante Briones", json.getString("lastname") );
        assertEquals("total price eslesmiyor", 111, json.getInt("totalprice") );
        assertEquals("depositpaid eslesmiyor", true, json.get("depositpaid") );
        assertEquals("Checkin date eslesmiyor", "2018-01-01", json.getString("bookingdates.checkin") );
        assertEquals("Checkout date eslesmiyor", "2019-01-01", json.getString("bookingdates.checkout") );


        //3.yol SoftAssert
        //i- SoftAssert objesini olusturma
//        SoftAssert softAssert = new SoftAssert();

       //ii- SoftAssert objesini kullanarak Assertion yapmak
//        softAssert.assertEquals(json.getString("firstname"),"Javier", "isimler eslesmiyor");
//        softAssert.assertEquals(json.getString("lastname"),"Dominguez", "Soyisimler eslesmiyor");
//        softAssert.assertEquals(json.getString("totalprice"),111, "Totalprice eslesmiyor");
//        softAssert.assertEquals(json.getString("depositpaid"),true, "Depositpaid eslesmiyor");
//        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01", "Checkin date eslesmiyor");
//        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01", "Checkout date eslesmiyor");


        //iii-MUTLAKA EN SONDA assertAll() yapilmali. Eger assertAll() kullanmazsaniz her zaman testiniz gecti gorunur fakat bu anlamli olmayabilir
//        softAssert.assertAll();

    }



}