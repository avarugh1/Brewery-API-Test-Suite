package tests.java.getBreweries;

import main.java.brewery.models.BreweryData;
import main.java.utilities.ReturnWrapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.java.AbstractTest;

import java.util.HashMap;
import java.util.List;

public class GetBreweriesTests extends AbstractTest {
    public ReturnWrapper<BreweryData> getBrewery(String id){
        ReturnWrapper<BreweryData> bd = breweries.getBrewery(id);
        Assert.assertEquals(200, bd.getResponse().getStatusCode(), "Response code was not 200!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");
        return bd;
    }

    @Test(description = "Get brewery by id: error")
    public void getBreweryByError(){
        ReturnWrapper<BreweryData> bd = breweries.getBrewery("error");
        Assert.assertEquals(404, bd.getResponse().getStatusCode(), "Response code was not 404!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");
    }

    @Test(groups = { "sanity" }, description = "Get brewery by id: 142")
    public void getBreweryBy142(){
        ReturnWrapper<BreweryData> bd = getBrewery("142");
        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() == 1, "Get ID endpoint did not return one object!");

        Assert.assertEquals(parsed.get(0).getName(), "Granite Mountain Brewing",
                "ID:142 was not Granite Mountain Brewing!");
    }
}
