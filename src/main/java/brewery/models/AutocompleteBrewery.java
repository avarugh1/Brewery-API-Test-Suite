package main.java.brewery.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutocompleteBrewery {
    @JsonProperty("id") int id;
    @JsonProperty("name") String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
