# Brewery API Test Suite
[![Build Status](https://travis-ci.org/avarugh1/Brewery-API-Test-Suite.svg?branch=master)](https://travis-ci.org/avarugh1/Brewery-API-Test-Suite)
[![Netlify Status](https://api.netlify.com/api/v1/badges/91a59f32-c5f9-43ae-bf79-10afa1ed317d/deploy-status)](https://app.netlify.com/sites/happy-kalam-893fb1/deploys)

Created by Anson Varughese

This is a test suite for the open source Brewery API (https://www.openbrewerydb.org/). 

Test suite is created in Java with TestNG, built and ran using Maven

Suite utilizes RestAssured for API requests, and Jackson for JSON Parsing and object creation

Project is built and ran on branch updates using Travis CI. 
The HTML Report is deployed to netlify at https://happy-kalam-893fb1.netlify.app/

## Building and running

If building and running using IntelliJ IDEA:

With Maven available, right click testng.xml and select 'Run ...'

If running for the first time and through cmd, utilize mvn install first:
```bash
mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V
mvn test -B
```

## Test Files

Test Files can be found under src/tests/java

## Requirements Analysis

:heavy_check_mark: Both happy path and a negative path scenarios can be found under src/tests/java/paths

:heavy_check_mark: All API tests have, at minimum, status code assertions and response time assertions

:heavy_check_mark: All other tests can be found in their respective folders under src/tests/java. 

:heavy_check_mark: All available filters are tested including sort functionality
