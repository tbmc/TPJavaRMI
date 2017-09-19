package iad.jms.chat;

import iad.rmi.chat.ChatConference;
import iad.rmi.chat.ChatConferenceImpl;

import java.rmi.RemoteException;

public class ChatJMSConferenceImpl extends ChatConferenceImpl implements ChatJMSConference
{

    private String pwd;

    public ChatJMSConferenceImpl(String n, String desc, String pwd) throws RemoteException
    {
        super(n, desc);

        this.pwd = pwd;
    }

    @Override
    public void activateLog(String pwd) throws RemoteException
    {

    }

    @Override
    public void desactivateLog(String pwd) throws RemoteException
    {

    }
}
