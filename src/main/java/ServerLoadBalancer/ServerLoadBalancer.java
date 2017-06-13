package ServerLoadBalancer;

import edu.iis.mto.serverloadbalancer.Server;
import edu.iis.mto.serverloadbalancer.Vm;

/**
 * Created by Patryk on 2017-06-13.
 */
public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        if(vms.length>0){
            servers[0].currentLoadPercentage = 100.0d;
        }
    }

}
