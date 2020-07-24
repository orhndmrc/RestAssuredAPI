import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DynamicJason {

    @Test(dataProvider = "PersonsData")
    public void addPerson(String name, String job){
        RestAssured.baseURI = "https://reqres.in";
        String response = given().log().all().body(payload.AddPerson(name,job)).
                when().post("/api/users").
                then().log().all().assertThat().statusCode(201).
                extract().response().asString();
        JsonPath js  = ReusableMethods.rawToJson(response);
        String id = js.get("id");
        System.out.println(id);

        //delete the person


    }
    @DataProvider(name = "PersonsData")
    public Object[][] getData(){
        Object[][] data = {{"Mustafa","police"},{"Aydogan","literature"},{"Mucahit","soldier"}};

        return data;
    }
}
