package main.java.brewery;

import main.java.brewery.models.AutocompleteBrewery;
import main.java.brewery.models.BreweryData;
import main.java.utilities.ReturnWrapper;

import java.util.Map;

public class Breweries {
    private BreweriesAPIUtils api;

    public Breweries(String base){
        this.api = new BreweriesAPIUtils(base);
    }

    public ReturnWrapper<BreweryData> getListOfBreweries() {
        return api.getListOfBreweries();
    }

    public ReturnWrapper<BreweryData> getListOfBreweries(Map<String, ?> qp){
        return api.getListOfBreweries(qp);
    }

    public ReturnWrapper<BreweryData> getBrewery(String id){
        return api.getBrewery(id);
    }

    public ReturnWrapper<BreweryData> searchBreweries(Map<String, ?> qp){
        return api.searchBreweries(qp);
    }

    public ReturnWrapper<AutocompleteBrewery> getAutoComplete(Map<String, ?> qp){
        return api.getAutocompleteBreweries(qp);
    }
}
