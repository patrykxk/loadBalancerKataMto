package edu.iis.mto.serverloadbalancer;

/**
 * Created by Patryk on 2017-06-13.
 */
public class ServerBuilder implements Builder<Server> {
    private int capacity;
    private double initialLoad;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server(capacity);
        if(initialLoad>0) {
            int initialVmSize = (int) ((double) initialLoad / (double) capacity * 100.0d);
            Vm initialVm = new VmBuilder().ofSize(initialVmSize).build();
            server.addVm(initialVm);
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
