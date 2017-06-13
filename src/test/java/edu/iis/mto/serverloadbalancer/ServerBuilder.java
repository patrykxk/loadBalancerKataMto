package edu.iis.mto.serverloadbalancer;

/**
 * Created by Patryk on 2017-06-13.
 */
public class ServerBuilder implements Builder<Server> {
    private int capacity;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        return new Server();
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }
}
