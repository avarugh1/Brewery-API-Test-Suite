package tests.java;

import main.java.brewery.Breweries;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public abstract class AbstractTest {
    public Breweries breweries;

    @BeforeClass
    @Parameters("base")
    public void beforeSuite(String url){
        breweries = new Breweries(url);
    }
}
