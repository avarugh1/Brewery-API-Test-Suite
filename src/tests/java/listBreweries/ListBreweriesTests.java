package tests.java.listBreweries;

import main.java.brewery.models.BreweryData;
import main.java.utilities.ReturnWrapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.java.AbstractTest;

import java.util.HashMap;
import java.util.List;

public class ListBreweriesTests extends AbstractTest {
    @Test(description = "Get list of breweries without passing any query parameters")
    public void noQueryParameters(){
        ReturnWrapper<BreweryData> bd = breweries.getListOfBreweries();
        Assert.assertEquals(200, bd.getResponse().getStatusCode(), "Response code was not 200!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty with no query parameters!");
    }

    public ReturnWrapper<BreweryData> getList(HashMap<String, String> qp){
        ReturnWrapper<BreweryData> bd = breweries.getListOfBreweries(qp);
        Assert.assertEquals(200, bd.getResponse().getStatusCode(), "Response code was not 200!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");
        return bd;
    }

    @Test(description = "Get list of breweries by city: San Diego")
    public ReturnWrapper<BreweryData> byCity(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("by_city", "san diego");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        return bd;
    }

    @Test(description = "All breweries in the city of San Diego should be in the state of California")
    public void searchStateOfSanDiego(){
        ReturnWrapper<BreweryData> bd = byCity();
        List<BreweryData> parsed = bd.getBodyObjects();

        for(BreweryData tmp : parsed){
            Assert.assertEquals(tmp.getState(), "California",
                    "A brewery in San Diego was not in the state of California!");
        }
    }

    @Test(description = "Get breweries containing name: Cooper")
    public void getCooperBreweries(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("by_name", "cooper");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        for(BreweryData tmp : parsed){
            Assert.assertTrue(tmp.getName().toLowerCase().contains("cooper"),
                    "One of the records did not have 'Cooper' in the name!");
        }
    }

    @Test(description = "Get breweries by state: New York")
    public void getNewYorkBreweries(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("by_state", "new york");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        for(BreweryData tmp : parsed){
            Assert.assertTrue(tmp.getState().toLowerCase().contains("new york"),
                    "One of the records did not have 'new york' in the state!");
        }
    }

    @Test(description = "Get breweries by postal: 35222")
    public void get5DigitBreweries(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("by_postal", "35222");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        for(BreweryData tmp : parsed){
            Assert.assertEquals(tmp.getState(), "Alabama",
                    "A brewery in 35222 was not in the state of Alabama!");
        }
    }

    @Test(description = "Get breweries by postal: 35222-1932")
    public void get9DigitBreweries(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("by_postal", "35222-1932");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        for(BreweryData tmp : parsed){
            Assert.assertEquals(tmp.getState(), "Alabama",
                    "A brewery in 35222-1932 was not in the state of Alabama!");
        }
    }

    @Test(description = "Get breweries by type: error")
    public void getBreweriesTypeError(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("by_type", "error");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() == 0, "List endpoint returned not empty!");
    }

    @Test(description = "Get breweries by type: planning")
    public void getBreweriesTypePlanning() {
        HashMap<String, String> qp = new HashMap<>();
        qp.put("by_type", "planning");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        for (BreweryData tmp : parsed) {
            Assert.assertEquals(tmp.getBrewery_type(), "planning",
                    "Brewery was not in planning stage!");
        }
    }

    @Test(description = "Page offset: error")
    public void getPageError(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("page", "error");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");
    }

    @Test(description = "Page offset: 3")
    public void getPage3(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("page", "3");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");
    }

    @Test(description = "Get per page: 30")
    public void get30PerPage() {
        HashMap<String, String> qp = new HashMap<>();
        qp.put("per_page", "30");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        Assert.assertEquals(parsed.size(), 30, "Endpoint did not return 30 breweries!");
    }

    @Test(description = "Sort by name ascending")
    public void getNameAscending(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("sort", "-name");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        String previous = parsed.get(0).getName(); // empty string: guaranteed to be less than or equal to any other
        Assert.assertEquals("Zymurgy Brew Works", parsed.get(0).getName(),
                "First element should be Zymurgy Brew Works!");
    }

    @Test(description = "Sort by name descending")
    public void getNameDescending(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("sort", "+name");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        String previous = parsed.get(0).getName(); // empty string: guaranteed to be less than or equal to any other
        Assert.assertEquals("101 Brewery", parsed.get(0).getName(),
                "First element should be 101 Brewery!");
    }

    @Test(description = "Test with multiple parameters")
    public void testMultipleQueryParams(){
        HashMap<String, String> qp = new HashMap<>();
        qp.put("by_postal", "35222");
        qp.put("per_page", "2");
        ReturnWrapper<BreweryData> bd = getList(qp);

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");
        Assert.assertEquals(parsed.size(), 2, "Endpoint did not return 2 breweries!");

        for(BreweryData tmp : parsed){
            Assert.assertEquals(tmp.getState(), "Alabama",
                    "A brewery in 35222 was not in the state of Alabama!");
        }
    }
}
