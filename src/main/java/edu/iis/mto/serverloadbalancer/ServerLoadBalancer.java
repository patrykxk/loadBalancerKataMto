package edu.iis.mto.serverloadbalancer;

/**
 * Created by Patryk on 2017-06-11.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        for(Vm vm : vms){
            servers[0].addVm(vm);
        }
    }
}
