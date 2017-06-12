package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.Server.MAXIMUM_LOAD;

public class ServerBuilder implements Builder<Server>{

	private int capacity;
	private double initialLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		addVmTo(server);
		return server;
	}

	private void addVmTo(Server server) {
		if(initialLoad>0) {
			int initialVmSize = (int) (initialLoad / capacity * MAXIMUM_LOAD);
			Vm vm = VmBuilder.vm().ofSize(initialVmSize).build();
			server.addVms(vm);
		}
	}

	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double initialLoad) {
		this.initialLoad = initialLoad;
		return this;
	}
}
