package main.java.brewery;

import io.restassured.http.Method;
import io.restassured.response.Response;
import main.java.brewery.models.AutocompleteBrewery;
import main.java.brewery.models.BreweryData;
import main.java.utilities.HTTPClient;
import main.java.utilities.ReturnWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreweriesAPIUtils {
    private final String BASE;
    private final HTTPClient hc;

    public BreweriesAPIUtils(String base){
        this.BASE = base;
        hc = new HTTPClient();
    }

    public ReturnWrapper<AutocompleteBrewery> getAutocompleteBreweries(Map<String, ?> queryParams){
        String url = BASE + "breweries/autocomplete";
        return getBreweries(url, queryParams, AutocompleteBrewery.class);
    }

    public ReturnWrapper<BreweryData> getBrewery(String id){
        String url = BASE + "breweries/" + id;
        Response rp = hc.sendRequest(Method.GET, url, "", new HashMap<>(), new HashMap<>());

        BreweryData bd = rp.body().jsonPath().getObject(".", BreweryData.class);
        ReturnWrapper<BreweryData> rw = new ReturnWrapper<BreweryData>(bd, rp);
        return rw;
    }

    // overload for no query parameters
    public ReturnWrapper<BreweryData> searchBreweries(){ return this.searchBreweries(new HashMap<>());}

    public ReturnWrapper<BreweryData> searchBreweries(Map<String, ?> queryParams){
        String url = BASE + "breweries/search";
        return getBreweries(url, queryParams, BreweryData.class);
    }

    // overload for no query parameters
    public ReturnWrapper<BreweryData> getListOfBreweries(){
        return getListOfBreweries(new HashMap<>());
    }

    public ReturnWrapper<BreweryData> getListOfBreweries(Map<String, ?> queryParams){
        String url = BASE + "breweries";
        return getBreweries(url, queryParams, BreweryData.class);
    }

    // made generic to handle both BreweryData and AutocompleteObject for search
    private <T> ReturnWrapper<T> getBreweries(String url, Map<String, ?> queryParams, Class cls){
        Response rp = hc.sendRequest(Method.GET, url, "", new HashMap<>(), queryParams);

        List<T> bd = rp.body().jsonPath().getList(".", cls);
        return new ReturnWrapper<T>(bd, rp);
    }
}
