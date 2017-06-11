package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Patryk on 2017-06-11.
 */
public class LoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private final double expectedLoadPercentage;

    public LoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    protected boolean matchesSafely(Server server) {
        return expectedLoadPercentage == server.currentLoadPercentage || (Math.abs(expectedLoadPercentage - server.currentLoadPercentage) < 0.01d);

    }

    public void describeTo(Description description) {
        description.appendText("Server load percentage: ").appendValue(expectedLoadPercentage);
    }
}
