package iad.rmi.chat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServer
{
    public static void main(String[] args) throws RemoteException, UnknownHostException
    {
        System.setProperty("java.rmi.hostname", "172.27.110.43");
        Registry registry = LocateRegistry.createRegistry(1099);
        ChatConferenceImpl conf = new ChatConferenceImpl("G3PO's conf", "La conférence de G3PO");
        registry.rebind(conf.getName(), conf);
        conf.start();
        System.out.println("plop");
    }
}
