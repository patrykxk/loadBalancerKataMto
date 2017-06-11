package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.LoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

    @Test
    public void balancingServerWithNoWms_serverStaysEmpty() throws Exception {
        Server theServer = a(server().withCapacity(1));
        balance(aServerListWith(theServer),anEmptyListOfVms());

        assertThat(theServer, hasLoadPercentageOf(0.0d));
    }

    private void balance(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers, vms);
    }

    private Vm[] anEmptyListOfVms() {
        return new Vm[0];
    }

    private Server[] aServerListWith(Server... servers) {
        return servers;
    }

    private Server a(ServerBuilder serverBuilder) {
        return serverBuilder.build();
    }


}
