import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticJson {
    @Test
    public void addPerson() throws IOException {
        RestAssured.baseURI = "https://reqres.in";
        String response = given().log().all().body(GenerateStringFromResources("C:\\Users\\demir\\Desktop\\person.json")).
                when().post("/api/users").
                then().log().all().assertThat().statusCode(201).
                extract().response().asString();
        JsonPath js = ReusableMethods.rawToJson(response);
        String id = js.get("id");
        System.out.println(id);

        //delete the person
    }
    public static String GenerateStringFromResources(String path) throws IOException { // it helps us upload json file and convert into string
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
