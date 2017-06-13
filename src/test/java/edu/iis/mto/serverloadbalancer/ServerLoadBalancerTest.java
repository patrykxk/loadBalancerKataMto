package edu.iis.mto.serverloadbalancer;


import ServerLoadBalancer.ServerLoadBalancer;
import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.CurrentLoadMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ServerLoadBalancerTest {
    @Test
    public void itCompiles() {
        assertThat(true, equalTo(true));
    }


    @Test
    public void serverWithNoVms_shouldStayEmpty() throws Exception {
        Server theServer = a(server().withCapacity(1));
        balance(aServersListWith(theServer), anEmptyListOfVms());

        assertThat(theServer, hasCurrentLoadOf(0.0d));
    }

    @Test
    public void serverWithOneSlotAndOneVm_shouldBeFilled() throws Exception {
        Server theServer = a(server().withCapacity(1));
        Vm theVm = a(vm().ofSize(1));
        balance(aServersListWith(theServer), aVmListWith(theVm));

        assertThat(theServer, hasCurrentLoadOf(100.0d));
        assertThat("Server contains vm", theServer.contains(theVm));
    }

    private Vm[] aVmListWith(Vm... vms) {
        return vms;
    }

    private Vm a(VmBuilder vmBuilder) {
        return vmBuilder.build();
    }

    private VmBuilder vm() {
        return new VmBuilder();
    }

    private void balance(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers, vms);
    }

    private Vm[] anEmptyListOfVms() {
        return new Vm[0];
    }

    private Server[] aServersListWith(Server... servers) {
        return servers;
    }

    private Server a(ServerBuilder serverBuilder) {
        return serverBuilder.build();
    }

}
