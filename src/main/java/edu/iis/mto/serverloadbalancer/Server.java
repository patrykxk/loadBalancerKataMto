package edu.iis.mto.serverloadbalancer;

/**
 * Created by Patryk on 2017-06-13.
 */
public class Server {
    public double currentLoadPercentage;
    public int capacity;

    public Server(int capacity) {

        this.capacity = capacity;
    }

    public boolean contains(Vm theVm) {
        return  true;
    }
}
