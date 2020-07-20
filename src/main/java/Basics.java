import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) {
        //Validate if Add Place API is working as expected
        //Given--->all input details
        //When---->Submit the API--resource and http method go under this method
        //Then---->validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("qaclick123").header("Content-Type","application/json")
                .body(payload.AddPlace()).when().post("maps/api/place/add/json")
        .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
        .header("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
        //Add place with new address--> get place to validate if new address is present in response
        System.out.println(response);
        JsonPath js = new JsonPath(response);//for parsing json
        String placeId = js.getString("place_id ");
        System.out.println(placeId);

        //update place
        String newAddress = "Summer Walk, Africa";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

        String getPlaceResponse =  given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
                .when().get("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress,"fail");


    }
}
