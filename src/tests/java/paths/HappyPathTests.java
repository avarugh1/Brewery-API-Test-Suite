package tests.java.paths;

import main.java.brewery.models.AutocompleteBrewery;
import main.java.brewery.models.BreweryData;
import main.java.utilities.ReturnWrapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.java.AbstractTest;

import java.util.HashMap;
import java.util.List;

public class HappyPathTests extends AbstractTest {
    public AutocompleteBrewery userAutocomplete(String in) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("query", in);

        ReturnWrapper<AutocompleteBrewery> bd = breweries.getAutoComplete(hm);
        Assert.assertEquals(200, bd.getResponse().getStatusCode(), "Response code was not 200!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");

        List<AutocompleteBrewery> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        Assert.assertEquals(parsed.get(0).getName(), "Diving Dog Brewhouse",
                "The first autocomplete for 'dog' was not Diving Dog Brewhouse!");

        return parsed.get(0);
    }

    public BreweryData userSearchByName(String name){
        HashMap<String, String> hm = new HashMap<>();
        hm.put("query", name);

        ReturnWrapper<BreweryData> bd = breweries.searchBreweries(hm);
        Assert.assertEquals(200, bd.getResponse().getStatusCode(), "Response code was not 200!");
        Assert.assertTrue(bd.getResponse().getTime() < 3000, "Response Time was greater than 3 seconds!");

        List<BreweryData> parsed = bd.getBodyObjects();
        Assert.assertTrue(parsed.size() > 0, "List endpoint returned empty!");

        return parsed.get(0);
    }

    public void assertIDWasEqual(AutocompleteBrewery acb, BreweryData bd){
        Assert.assertEquals(acb.getId(), bd.getId(), "ID's were not equal!");
    }

    @Test(description = "Happy path for user flow for autocomplete, search, and id verification for string Dog")
    public void happyPath(){
        // User uses autocomplete to start searching for dog. User chooses first option
        AutocompleteBrewery acb = userAutocomplete("dog");

        // User types out entire first option name and hits search
        BreweryData bd = userSearchByName(acb.getName());

        // Both IDs should be equal
        assertIDWasEqual(acb, bd);
    }
}
