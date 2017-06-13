package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Patryk on 2017-06-13.
 */
public class CurrentLoadMatcher extends TypeSafeMatcher<Server>{
    public static final double EPSILON = 0.01d;
    private double expectedLoad;

    public CurrentLoadMatcher(double expectedLoad) {
        this.expectedLoad = expectedLoad;
    }

    protected boolean matchesSafely(Server server) {
        return isDoublesEqual(this.expectedLoad, server.currentLoadPercentage);
    }

    private boolean isDoublesEqual(double d1, double d2) {
        return d1 == d2 || Math.abs(d1 - d2) < EPSILON;
    }

    public void describeTo(Description description) {
        description.appendText("Server load of: ").appendValue(expectedLoad);
    }

    public static Matcher<? super Server> hasCurrentLoadOf(double expectedLoad) {
        return new CurrentLoadMatcher(expectedLoad);
    }

}
