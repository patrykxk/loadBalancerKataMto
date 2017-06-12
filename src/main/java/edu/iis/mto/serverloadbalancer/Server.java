package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 2017-06-11.
 */
public class Server {
    public static final double MAX_LOAD = 100.0d;
    private double currentLoadPercentage;
    private int capacity;
    private List<Vm> vms = new ArrayList<Vm>();

    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage = getCurrentLoadPercentage() + vmLoad(vm);
        vms.add(vm);
    }

    private double vmLoad(Vm vm) {
        return ((double) vm.getSize() / (double) this.capacity) * MAX_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return (getCurrentLoadPercentage() + vmLoad(vm)) <= MAX_LOAD;
    }

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }
}
