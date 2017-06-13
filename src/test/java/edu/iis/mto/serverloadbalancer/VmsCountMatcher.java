package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Patryk on 2017-06-13.
 */
public class VmsCountMatcher extends TypeSafeMatcher<Server>{
    private int expectedVmCount;

    public VmsCountMatcher(int expectedVmCount) {
        this.expectedVmCount = expectedVmCount;
    }

    protected boolean matchesSafely(Server server) {
        return expectedVmCount == server.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("Vms count of: ").appendValue(item.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("Vms count of: ").appendValue(expectedVmCount);
    }
}
