package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.Server.*;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
    private double initialLoad;

    public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
        Server server = addInitialLoad();
        return server;
	}

    private Server addInitialLoad() {
        Server server = new Server(capacity);
        if(initialLoad>0) {
            int vmInitialSize = (int) ((initialLoad / capacity) * MAXIMUM_LOAD);
            Vm vm = VmBuilder.vm().ofSize(vmInitialSize).build();
            server.addVm(vm);
        }
        return server;
    }

    public static ServerBuilder server() {
		return new ServerBuilder();
	}

    public ServerBuilder withCurrentLoadOf(double initialLoad) {
        this.initialLoad = initialLoad;
        return this;
    }
}
