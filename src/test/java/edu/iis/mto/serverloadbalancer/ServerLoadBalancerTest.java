package edu.iis.mto.serverloadbalancer;


import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.LoadPercentageMatcher.hasLoadPercentageOf;
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
    public void balancingServerWithNoWms_serverStaysEmpty() throws Exception {
        Server theServer = a(server().withCapacity(1));
        balance(aServerListWith(theServer), anEmptyListOfVms());

        assertThat(theServer, hasLoadPercentageOf(0.0d));
    }

    @Test
    public void balancingServerWithOneVM_serverFills() throws Exception {
        Server theServer = a(server().withCapacity(1));
        Vm theVm = a(vm().withSize(1));
        balance(aServerListWith(theServer), aVmsListWith(theVm));

        assertThat(theServer, hasLoadPercentageOf(100.0d));
        assertThat("Server contains vm", theServer.contains(theVm));
    }
    @Test
    public void balancingServerWithTenSlotsOneVM_serverFillsWithTenPercent() throws Exception {
        Server theServer = a(server().withCapacity(10));
        Vm theVm = a(vm().withSize(1));
        balance(aServerListWith(theServer), aVmsListWith(theVm));

        assertThat(theServer, hasLoadPercentageOf(10.0d));
    assertThat("Server contains vm", theServer.contains(theVm));
    }

    private Vm[] aVmsListWith(Vm... vms) {
        return vms;
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

    private <T> T a(Builder<T> builder){
        return builder.build();
    }
}
