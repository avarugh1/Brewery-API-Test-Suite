package main.java.utilities;

import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class ReturnWrapper<T> {
    List<T> bodyObjects;
    Response response;

    public ReturnWrapper(T bodyParsed_p, Response rp_p){
        this.bodyObjects = new ArrayList<>();
        this.bodyObjects.add(bodyParsed_p);
        this.response = rp_p;
    }

    public ReturnWrapper(List<T> bodyParsed_p, Response rp_p){
        this.bodyObjects = bodyParsed_p;
        this.response = rp_p;
    }

    public List<T> getBodyObjects() {
        return bodyObjects;
    }

    public Response getResponse() {
        return response;
    }

}