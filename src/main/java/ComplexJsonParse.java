import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.CoursePrice());
        //print number of courses returned by API
        int courseAmount = js.getInt("courses.size()");
        System.out.println("Course amount : "+courseAmount);
        //Print Purchase amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase amount : "+purchaseAmount);
        //Print title of the first course
        String firstTitle = js.get("courses[0].title");
        System.out.println("First course title : "+firstTitle);
        //Print all titles and their titles
        for (int i = 0; i <courseAmount ; i++) {
            String titles = js.get("courses["+i+"].title");
            System.out.println(titles);
            int prices = js.getInt("courses["+i+"].price");
            System.out.println(prices);
            System.out.println("**************");
        }
        //Print the number of copies of RPA
        for (int i = 0; i <courseAmount ; i++) {
            String titles = js.get("courses["+i+"].title");
          if(titles.equals("RPA")){
              int copies = js.getInt("courses["+i+"].copies");
              System.out.println("Number of copies of course RPA : "+copies);
              break;

          }
        }
        //TODO: I rewrite the codes below in SumValidation class to use testNG Assertion not in main method
        //Verify if sum of all course prices matches with purchase amount
        int sum = 0;
        //int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        for (int i = 0; i <courseAmount ; i++) {
            int prices = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int totalPriceforEachCourse = prices*copies;
            System.out.println(totalPriceforEachCourse);
            sum+=totalPriceforEachCourse;
        }
        Assert.assertEquals(purchaseAmount,sum,"not equal");


    }
}
