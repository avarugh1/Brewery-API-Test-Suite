package tests.java.searchBreweries;

import main.java.brewery.models.BreweryData;
import main.java.utilities.ReturnWrapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.java.AbstractTest;

import java.util.HashMap;
import java.util.List;

public class SearchBreweriesTests extends AbstractTest {
    public ReturnWrapper<BreweryData> getList(String qp){
        HashMap<String, String> hm = new HashMap<>();
        hm.put("query", qp);

        ReturnWrapper<BreweryData> bd = breweries.searchBreweries(hm);
        Assert.assertEquals(200, bd.getResponse().getStatusCode(), "Response code was not 200!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");
        return bd;
    }

    @Test(description = "Search for query with no results")
    public void searchEmptyResults(){
        ReturnWrapper<BreweryData> bd = getList("testingnopossiblecombination");

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() == 0, "List endpoint did not return empty!");
    }

    @Test(description = "Search for query: dog")
    public void searchDog(){
        ReturnWrapper<BreweryData> bd = getList("dog");

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");
    }
}
