package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
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
	public void balancingOneServerWithTenSlotCapacity_andOneSlotVm_fillsTheServerWithTenPercent() {
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(10.0d));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}
    @Test
    public void balancingOneServerWithEnoughRoom_getsFilledWithAllVms() {
        Server theServer = a(server().withCapacity(100));
        Vm theFirstVm = a(vm().ofSize(1));
        Vm theSecondVm = a(vm().ofSize(1));

        balance(aListOfServersWith(theServer), aListOfVmsWith(theFirstVm, theSecondVm));

        assertThat(theServer, hasCountOfVms(2));
        assertThat("the server should contain first vm", theServer.contains(theFirstVm));
        assertThat("the server should contain second vm", theServer.contains(theSecondVm));
    }

    private Matcher<? super Server> hasCountOfVms(int expectedCountOfVms) {
        return new CountOfVmsMatcher(expectedCountOfVms);
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

	private Server[] aListOfServersWith(Server server) {
		return new Server[] { server };
	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}
}
