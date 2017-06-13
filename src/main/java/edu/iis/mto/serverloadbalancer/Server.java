package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 2017-06-13.
 */
public class Server {
    public double currentLoadPercentage;
    public int capacity;
    public static final double MAXIMUM_LOAD = 100.0d;
    private List<Vm> vms = new ArrayList<Vm>();

    public Server(int capacity) {

        this.capacity = capacity;
    }

    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }

    public void addVm(Vm vm) {
        currentLoadPercentage =  (double)vm.size / (double)capacity * MAXIMUM_LOAD;
        vms.add(vm);
    }

    public int countVms() {
        return vms.size();
    }
}
