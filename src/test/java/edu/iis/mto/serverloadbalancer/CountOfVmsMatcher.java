package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Patryk on 2017-06-11.
 */
public class CountOfVmsMatcher extends TypeSafeMatcher<Server> {
    private final int expectedCountOfVms;

    public CountOfVmsMatcher(int expectedCountOfVms) {
        this.expectedCountOfVms = expectedCountOfVms;
    }

    protected boolean matchesSafely(Server server) {
        return expectedCountOfVms == server.countVms();
    }

    @Override
    protected void describeMismatchSafely(Server item, Description mismatchDescription) {
        mismatchDescription.appendText("Server with count of vms: ").appendValue(item.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("Server with count of vms: ").appendValue(expectedCountOfVms);
    }

    public static Matcher<? super Server> hasCountOfVms(int expectedCountOfVms) {
        return new CountOfVmsMatcher(expectedCountOfVms);
    }
}
