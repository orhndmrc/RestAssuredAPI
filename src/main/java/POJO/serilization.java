package POJO;

import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serilization {
    public static void main(String[] args) {
        pojoserialization p = new pojoserialization();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setName("Frontline house");
        List<String> mylist = new ArrayList<String>();
        mylist.add("shoe park");
        mylist.add("shop");
        p.setTypes(mylist);

        location l = new location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        RestAssured.baseURI="https://rahulshettyacademy.com";
       String response =  given().log().all().queryParam("key","qaclick123").body(p)
                .when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }
}
