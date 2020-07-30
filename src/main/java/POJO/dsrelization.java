package POJO;


import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.given;

public class dsrelization {
    public static void main(String[] args) {

        pojodiserilazition gc = given().queryParam("key","qaclick123").expect().defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/maps/api/place/add/json")
                .as(pojodiserilazition.class);
        System.out.println(gc.getPlace_id());
        System.out.println(gc.getScope());
    }


}
