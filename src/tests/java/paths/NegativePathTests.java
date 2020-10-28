package tests.java.paths;

import main.java.brewery.models.AutocompleteBrewery;
import main.java.brewery.models.BreweryData;
import main.java.utilities.ReturnWrapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.java.AbstractTest;

import java.util.HashMap;
import java.util.List;

public class NegativePathTests extends AbstractTest {
    public AutocompleteBrewery userAutocomplete(String in) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("query", in);

        ReturnWrapper<AutocompleteBrewery> bd = breweries.getAutoComplete(hm);
        Assert.assertEquals(200, bd.getResponse().getStatusCode(), "Response code was not 200!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");

        List<AutocompleteBrewery> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() == 0, "List endpoint did not return empty!");

        return null;
    }

    public BreweryData userSearchByName(String name){
        HashMap<String, String> hm = new HashMap<>();
        hm.put("query", name);

        ReturnWrapper<BreweryData> bd = breweries.searchBreweries(hm);
        Assert.assertEquals(200, bd.getResponse().getStatusCode(), "Response code was not 200!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() == 0, "List endpoint did not return empty!");

        return null;
    }

    @Test(groups = { "sanity" }, description = "User incorrectly enters autocomplete data. User incorrectly searchs for that data")
    public void negativeTests() {
        // User uses autocomplete to start searching for nonexistent data.
        AutocompleteBrewery acb = userAutocomplete("lasjdlfajsldfa");
        Assert.assertNull(acb);

        // User types out entire nonexistent name and hits search
        BreweryData bd = userSearchByName("lasjdlfajsldfa");
        Assert.assertNull(bd);
    }
}
