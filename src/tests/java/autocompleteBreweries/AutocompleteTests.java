package tests.java.autocompleteBreweries;

import main.java.brewery.models.AutocompleteBrewery;
import main.java.brewery.models.BreweryData;
import main.java.utilities.ReturnWrapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.java.AbstractTest;

import java.util.HashMap;
import java.util.List;

public class AutocompleteTests extends AbstractTest {
    public ReturnWrapper<AutocompleteBrewery> getList(String qp){
        HashMap<String, String> hm = new HashMap<>();
        hm.put("query", qp);

        ReturnWrapper<AutocompleteBrewery> bd = breweries.getAutoComplete(hm);
        Assert.assertEquals(200, bd.getResponse().getStatusCode(), "Response code was not 200!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");
        return bd;
    }

    @Test(description = "Get Autocomplete for non existent search option")
    public void getAutocompleteEmpty(){
        ReturnWrapper<AutocompleteBrewery> bd = getList("testingnoreturnshouldcomeback");

        List<AutocompleteBrewery> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() == 0, "List endpoint returned empty!");
    }

    @Test(groups = { "sanity" }, description = "Get Autocomplete for dog")
    public void getAutocompleteDog(){
        ReturnWrapper<AutocompleteBrewery> bd = getList("dog");

        List<AutocompleteBrewery> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        Assert.assertEquals(parsed.get(0).getName(), "Diving Dog Brewhouse",
                "The first autocomplete for 'dog' was not Diving Dog Brewhouse!");
    }
}
