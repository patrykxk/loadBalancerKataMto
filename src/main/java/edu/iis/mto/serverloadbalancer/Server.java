package edu.iis.mto.serverloadbalancer;


public class Server {

	public double currentLoadPecentage;
    private int capacity;

    public Server(int capacity) {
	    this.capacity = capacity;
    }

	public boolean contains(Vm vm) {
		return true;
	}

    public void addVms(Vm[] vms) {
        currentLoadPecentage = (double)vms[0].size / (double) this.capacity * 100.0d;
    }
}
