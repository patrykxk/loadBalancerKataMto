package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Patryk on 2017-06-11.
 */
public class LoadPercentageMatcher extends TypeSafeMatcher<Server> {
    public static final double EPSILON = 0.01d;
    private final double expectedLoadPercentage;

    public LoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    protected boolean matchesSafely(Server server) {
        return isDoublesEqual(this.expectedLoadPercentage, server.currentLoadPercentage);
    }

    private boolean isDoublesEqual(double d1, double d2) {
        return d1 == d2 || (Math.abs(d1 - d2) < EPSILON);
    }

    public void describeTo(Description description) {
        description.appendText("Server load percentage: ").appendValue(expectedLoadPercentage);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("Server load percentage: ").appendValue(item.currentLoadPercentage);
    }

    public static Matcher<? super Server> hasLoadPercentageOf(double expectedLoadPercentage) {
        return new LoadPercentageMatcher(expectedLoadPercentage);
    }
}
