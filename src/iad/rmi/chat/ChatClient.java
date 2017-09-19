package iad.rmi.chat;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.Thread.sleep;

public class ChatClient
{

    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException
    {
        Registry registry = LocateRegistry.getRegistry(args[0], 1099);
        ChatConference conf2 = (ChatConference) registry.lookup(registry.list()[0]);
        ChatParticipant participant = new ChatParticipantImpl(args[1]);
        participant.join(conf2);

        if (participant.isConnected())
            new ChatClientConsole(conf2, participant).start();
    }

}
