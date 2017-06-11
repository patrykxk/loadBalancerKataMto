package edu.iis.mto.serverloadbalancer;

/**
 * Created by Patryk on 2017-06-11.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        if(vms.length>0) {
            servers[0].currentLoadPercentage = ((double) vms[0].size / (double) servers[0].capacity) * 100.0d;
        }
    }
}
