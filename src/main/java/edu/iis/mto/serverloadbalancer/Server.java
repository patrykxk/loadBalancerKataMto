package edu.iis.mto.serverloadbalancer;

/**
 * Created by Patryk on 2017-06-11.
 */
public class Server {
    public double currentLoadPercentage;
    public int capacity;

    public boolean contains(Vm theVm) {
        return true;
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }
}
