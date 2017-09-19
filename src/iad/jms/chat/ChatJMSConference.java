package iad.jms.chat;

import iad.rmi.chat.ChatConference;
import iad.rmi.chat.ChatMessage;
import iad.rmi.chat.ChatParticipant;

import java.rmi.RemoteException;
import java.util.HashMap;

public class ChatJMSConference implements ChatConference
{
    protected String name, description;
    protected HashMap<String, ChatParticipant> participants;
    protected Boolean isStarted;

    @Override
    public String getName() throws RemoteException
    {
        return null;
    }

    @Override
    public String getDescription() throws RemoteException
    {
        return null;
    }

    @Override
    public boolean isStarted() throws RemoteException
    {
        return false;
    }

    @Override
    public void addParticipant(ChatParticipant p) throws RemoteException
    {

    }

    @Override
    public void removeParticipant(ChatParticipant p) throws RemoteException
    {

    }

    @Override
    public String[] participants() throws RemoteException
    {
        return new String[0];
    }

    @Override
    public void broadcast(ChatMessage message) throws RemoteException
    {

    }

    @Override
    public void start() throws RemoteException
    {

    }

    @Override
    public void stop() throws RemoteException
    {

    }
}
