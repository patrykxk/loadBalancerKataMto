package edu.iis.mto.serverloadbalancer;


import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAXIMAL_LOAD = 100.0d;
    public double currentLoadPercentage;
    private int capacity;
    private List<Vm> vms = new ArrayList<Vm>();
    public Server(int capacity) {
	    this.capacity = capacity;
    }

	public boolean contains(Vm vm) {
		return vms.contains(vm);
	}

    public void addVms(Vm vm) {
        currentLoadPercentage = (double)vm.size / (double) this.capacity * MAXIMAL_LOAD;
        vms.add(vm);
    }

    public int countVms() {
        return vms.size();
    }
}
