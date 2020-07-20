import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfCourse(){
        JsonPath js = new JsonPath(payload.CoursePrice());
        int courseAmount = js.getInt("courses.size()");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        int sum = 0;
        for (int i = 0; i <courseAmount ; i++) {
            int prices = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int totalPriceforEachCourse = prices*copies;
            sum+=totalPriceforEachCourse;
        }
        Assert.assertEquals(purchaseAmount,sum,"not equal");


    }
    }

