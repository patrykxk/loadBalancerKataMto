package edu.iis.mto.serverloadbalancer;


import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    private int capacity;
    private List<Vm> vms = new ArrayList();

    public Server(int capacity) {
	    this.capacity = capacity;
    }

	public boolean contains(Vm vm) {
		return true;
	}

    public void addVms(Vm vm) {
        currentLoadPercentage = (double)vm.size / (double) this.capacity * MAXIMUM_LOAD;
        this.vms.add(vm);
    }

    public int countVms() {
        return vms.size();
    }
}
