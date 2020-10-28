package main.java.brewery.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BreweryData {
    @JsonProperty("id") int id;
    @JsonProperty("name") String name;
    @JsonProperty("brewery_type") String brewery_type;
    @JsonProperty("street") String street;
    @JsonProperty("address_2") String address_2;
    @JsonProperty("address_3") String address_3;
    @JsonProperty("city") String city;
    @JsonProperty("state") String state;
    @JsonProperty("county_province") String county_province;
    @JsonProperty("postal_code") String postal_code;
    @JsonProperty("country") String country;
    @JsonProperty("longitude") String longitude;
    @JsonProperty("latitude") String latitude;
    @JsonProperty("phone") String phone; // support for any (+) chars like (+91) or dashes
    @JsonProperty("website_url") String website_url;
    @JsonProperty("updated_at") String updated_at;
    @JsonProperty("created_at") String created_at;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrewery_type() {
        return brewery_type;
    }

    public String getStreet() {
        return street;
    }

    public String getAddress_2() {
        return address_2;
    }

    public String getAddress_3() {
        return address_3;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCounty_province() {
        return county_province;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getCountry() {
        return country;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite_url() {
        return website_url;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }
}
