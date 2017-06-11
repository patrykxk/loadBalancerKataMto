package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Patryk on 2017-06-11.
 */
public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private final int expectedVmsCount;

    public ServerVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    protected boolean matchesSafely(Server server) {
        return expectedVmsCount == server.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("Server vms count: ").appendValue(item.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("Server vms count: ").appendValue(expectedVmsCount);
    }
    public static Matcher<? super Server> hasAVMsCountOf(int expectedVmsCount) {
        return new ServerVmsCountMatcher(expectedVmsCount);
    }
}
