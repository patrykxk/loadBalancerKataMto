package ServerLoadBalancer;

import edu.iis.mto.serverloadbalancer.Server;
import edu.iis.mto.serverloadbalancer.Vm;

/**
 * Created by Patryk on 2017-06-13.
 */
public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        if(vms.length>0){
            addVmsToLessLoadedServer(servers, vms);
        }
    }

    private void addVmsToLessLoadedServer(Server[] servers, Vm[] vms) {
        for (Vm vm :
                vms) {
            Server lessLoadedServer = findLessLoadedServer(servers);
            lessLoadedServer.addVm(vm);
        }
    }

    private Server findLessLoadedServer(Server[] servers) {
        Server lessLoadedServer = null;
        for (Server server :
                servers) {
            if(lessLoadedServer == null || server.currentLoadPercentage < lessLoadedServer.currentLoadPercentage){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }

}
