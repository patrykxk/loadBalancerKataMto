package edu.iis.mto.serverloadbalancer;


import java.util.ArrayList;
import java.util.List;

public class Server {

	public double currentLoadPercentage;
    private int capacity;
    private List<Vm> vms = new ArrayList<Vm>();
    public Server(int capacity) {
	    this.capacity = capacity;
    }

	public boolean contains(Vm vm) {
		return true;
	}

    public void addVms(Vm vm) {
        currentLoadPercentage = (double)vm.size / (double) this.capacity * 100.0d;
        vms.add(vm);
    }

    public int countVms() {
        return vms.size();
    }
}
