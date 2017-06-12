package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasAVMsCountOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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
    public void balancingAServer_noVms_serverStaysEmpty() {
        Server theServer = a(server().withCapacity(1));

        balance(aListOfServersWith(theServer), anEmptyListOfVms());

        assertThat(theServer, hasLoadPercentageOf(0.0d));
    }

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm() {
        Server theServer = a(server().withCapacity(1));
        Vm theVm = a(vm().ofSize(1));
        balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

        assertThat(theServer, hasLoadPercentageOf(100.0d));
        assertThat("the server should contain vm", theServer.contains(theVm));
    }

    @Test
    public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillsTheServerWithTenPercent() {
        Server theServer = a(server().withCapacity(10));

        Vm theVm = a(vm().ofSize(1));
        balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

        assertThat(theServer, hasLoadPercentageOf(10.0d));
        assertThat("the server should contain vm", theServer.contains(theVm));
    }

    @Test
    public void balancingServerWithEnoughRoom_fillsTheServerWithAllVms() throws Exception {
        Server theServer = a(server().withCapacity(100));

        Vm theFirstVm = a(vm().ofSize(1));
        Vm theSecondVm = a(vm().ofSize(1));
        balance(aListOfServersWith(theServer), aListOfVmsWith(theFirstVm, theSecondVm));

        assertThat(theServer, hasAVMsCountOf(2));
        assertThat("the server should contain first vm", theServer.contains(theFirstVm));
        assertThat("the server should contain second vm", theServer.contains(theSecondVm));

    }
    @Test
    public void vmShouldBeBalancedOnLessLoadedServer() throws Exception {
        Server moreLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(50.0d));
        Server lessLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(45.0d));

        Vm theVm = a(vm().ofSize(1));
        balance(aListOfServersWith(moreLoadedServer, lessLoadedServer), aListOfVmsWith(theVm));

        assertThat("Less loaded server should contain first vm", lessLoadedServer.contains(theVm));
        assertThat("More loaded server should not contain second vm", !moreLoadedServer.contains(theVm));
    }

	@Test
	public void overloadedServerShouldNotBeFilledWithVm() throws Exception {
		Server theServer = a(server().withCapacity(100).withCurrentLoadOf(95.0d));

		Vm theVm = a(vm().ofSize(10));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat("Overloaded server should not contain second vm", !theServer.contains(theVm));
	}
	@Test
	public void balancingServersAndVms() throws Exception {
		Server server1 = a(server().withCapacity(4));
		Server server2 = a(server().withCapacity(5));

		Vm vm1 = a(vm().ofSize(1));
		Vm vm2 = a(vm().ofSize(4));
		Vm vm3 = a(vm().ofSize(2));
		balance(aListOfServersWith(server1,server2), aListOfVmsWith(vm1,vm2,vm3));

		assertThat("Server 1 should not contain vm1", server1.contains(vm1));
		assertThat("Server 2 should not contain vm2", server2.contains(vm2));
		assertThat("Server 1 should not contain vm3", server1.contains(vm3));

		assertThat(server1, hasLoadPercentageOf(75.0d));
		assertThat(server2, hasLoadPercentageOf(80.0d));

	}

    private void balance(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers, vms);
    }

    private Vm[] aListOfVmsWith(Vm... vms) {
        return vms;
    }

    private Vm[] anEmptyListOfVms() {
        return new Vm[0];
    }

    private Server[] aListOfServersWith(Server... servers) {
        return servers;
    }

    private <T> T a(Builder<T> builder) {
        return builder.build();
    }
}
