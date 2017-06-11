package edu.iis.mto.serverloadbalancer;


public class Server {

    public static final double MAXIMAL_LOAD = 100.0d;
    public double currentLoadPercentage;
	public int capacity;

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm vm) {
		return true;
	}

    public void addVms(Vm[] vms) {
        currentLoadPercentage = (double) vms[0].size / (double)this.capacity * MAXIMAL_LOAD;
    }
}
