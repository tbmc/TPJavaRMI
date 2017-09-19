package iad.jms.chat;

import iad.rmi.chat.ChatConference;

import java.rmi.RemoteException;

public interface ChatJMSConference extends ChatConference
{
    // Active le placement en file des messages
    void activateLog(String pwd) throws RemoteException;

    // Désactive le placement en file des messages
    void desactivateLog(String pwd) throws RemoteException;

}
