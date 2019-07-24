package renesans.core;


import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    @Before
    public void startup() {
        Initial.startBrowser();
    }

    @After
    public void finish() {
        Initial.getDriver().close();
        Initial.getDriver().quit();
    }
}
