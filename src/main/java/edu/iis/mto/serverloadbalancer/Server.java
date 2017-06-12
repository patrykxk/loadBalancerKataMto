package edu.iis.mto.serverloadbalancer;


import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAXIMAL_LOAD = 100.0d;
    private double currentLoadPercentage;
    private int capacity;
    private List<Vm> vms = new ArrayList<Vm>();
    public Server(int capacity) {
	    this.capacity = capacity;
    }

	public boolean contains(Vm vm) {
		return vms.contains(vm);
	}

    public void addVms(Vm vm) {
        currentLoadPercentage = getCurrentLoadPercentage() + loadOfVm(vm);
        vms.add(vm);
    }

    private double loadOfVm(Vm vm) {
        return (double) vm.getSize() / (double) this.capacity * MAXIMAL_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return (getCurrentLoadPercentage() + (loadOfVm(vm))) <= MAXIMAL_LOAD ;
    }

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }
}
