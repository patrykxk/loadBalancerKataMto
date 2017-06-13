package edu.iis.mto.serverloadbalancer;


import ServerLoadBalancer.ServerLoadBalancer;
import org.hamcrest.Matcher;
import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.CurrentLoadMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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
    @Test
    public void balaningServerWithTenSlotsCapacity_andOneSlotVm_fillServerWithTenPercent() throws Exception {
        Server theServer = a(server().withCapacity(10));
        Vm theVm = a(vm().ofSize(1));
        balance(aServersListWith(theServer), aVmListWith(theVm));

        assertThat(theServer, hasCurrentLoadOf(10.0d));
        assertThat("Server contains vm", theServer.contains(theVm));
    }

    @Test
    public void balaningServerWithEnoughRoom_getsFilledWithAllVms() throws Exception {
        Server theServer = a(server().withCapacity(10));
        Vm theFirstVm = a(vm().ofSize(1));
        Vm theSecondVm = a(vm().ofSize(1));

        balance(aServersListWith(theServer), aVmListWith(theFirstVm, theSecondVm));

        assertThat("Server contains first vm", theServer.contains(theFirstVm));
        assertThat("Server contains second vm", theServer.contains(theSecondVm));
        assertThat(theServer, hasVmsCoutOf(2));
    }

    private Matcher<? super Server> hasVmsCoutOf(int expectedVmCount) {
        return new VmsCountMatcher(expectedVmCount);
    }

    private Vm[] aVmListWith(Vm... vms) {
        return vms;
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

    private <T> T a(Builder<T> builder){
        return builder.build();
    }

}
