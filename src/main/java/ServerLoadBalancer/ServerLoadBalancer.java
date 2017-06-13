package ServerLoadBalancer;

import edu.iis.mto.serverloadbalancer.Server;
import edu.iis.mto.serverloadbalancer.Vm;

/**
 * Created by Patryk on 2017-06-13.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        if(vms.length>0){
            for (Vm vm :
                    vms) {
                servers[0].addVm(vm);
            }
        }
    }

}
