package iad.rmi.chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Set;

public class ChatConferenceImpl extends UnicastRemoteObject implements ChatConference
{

    protected String name, description;
    protected HashMap<String, ChatParticipant> participants;
    protected Boolean isStarted;

    public ChatConferenceImpl(String n, String desc) throws RemoteException
    {
        this.name = n;
        this.description = desc;
        participants = new HashMap<String, ChatParticipant>();
        isStarted = false;
    }

    @Override
    public String getName() throws RemoteException
    {
        return name;
    }

    @Override
    public String getDescription() throws RemoteException
    {
        return description;
    }

    @Override
    public boolean isStarted() throws RemoteException
    {
        return isStarted;
    }

    @Override
    public void addParticipant(ChatParticipant p) throws RemoteException
    {
        String pname = p.getName();
        if (!participants.containsKey(pname))
        {
            participants.put(pname, p);
            broadcast(new ChatMessage(name, pname + " a rejoint le chat!"));
        }

    }

    @Override
    public void removeParticipant(ChatParticipant p) throws RemoteException
    {
        String pname = p.getName();
        if (participants.containsKey(pname))
        {
            participants.remove(pname);
            broadcast(new ChatMessage(name, pname + " a quitté le chat!"));
        }
    }

    @Override
    public String[] participants() throws RemoteException
    {
        Set<String> remote = participants.keySet();
        String[] remoteRef = new String[remote.size()];
        return remote.toArray(remoteRef);
    }

    @Override
    public void broadcast(ChatMessage message) throws RemoteException
    {
        if (isStarted)
        {

            String emmeteur = message.getEmitter();
            for (String pname : participants.keySet())
            {
                if (!pname.equals(emmeteur))
                {
                    participants.get(pname).process(message);
                }
            }
        }
    }

    @Override
    public void start() throws RemoteException
    {
        if (!isStarted())
        {
            isStarted = true;
            broadcast(new ChatMessage(name, "Conference Started"));
        }

    }

    @Override
    public void stop() throws RemoteException
    {
        if (isStarted())
        {
            isStarted = false;
            broadcast(new ChatMessage(name, "Conference Stopped"));
        }

    }

}
