package main.java.utilities;

import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HTTPClient {
    public Response sendRequest(Method httpMethod, String url, String payload,
                                Map<String, ?> headers, Map<String, ?> params){
        Response response;
        switch(httpMethod){
            case GET:
                response = given()
                        .params(params)
                        .urlEncodingEnabled(true)
                        .headers(headers)
                        .get(url);
                break;
            case POST:
                response = given()
                        .headers(headers)
                        .body(payload)
                        .post(url);
                break;
            default:
                // undefined httpMethod!
                response = null;
        }
        return response;
    }
}
