package iad.rmi.chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ChatParticipantImpl extends UnicastRemoteObject implements ChatParticipant{
    protected String name;
    protected ChatConference currentConf;
    protected BlockingQueue<ChatMessage> msgQueue;
    protected Boolean isConnected;

    public ChatParticipantImpl(String nm) throws RemoteException{
        msgQueue = new LinkedBlockingDeque<ChatMessage>();
        currentConf = null;
        isConnected = false;
        name = nm;

    }

    @Override
    public String getName() throws RemoteException{
        return name;
    }

    @Override
    public boolean isConnected() throws RemoteException {
        return isConnected;
    }

    @Override
    public boolean join(ChatConference conf) throws RemoteException{
        if(!isConnected){
            try {
                conf.addParticipant(this);
                currentConf = conf;
                isConnected = true;
                System.out.println(conf.getName()+": a été rejointe");
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }return false;
    }

    @Override
    public void leave(ChatConference conf) throws RemoteException{
        if (isConnected){
            try {
                conf.removeParticipant(this);
                currentConf = null;
                isConnected = false;
                System.out.println(conf.getName()+": a été quittée");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void send(String msg) throws RemoteException{
        if (isConnected) {
            try {
                currentConf.broadcast(new ChatMessage(name, msg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("Vous devez être connecté");
        }
    }

    @Override
    public void process(ChatMessage msg) throws RemoteException {
        if(isConnected){
            try {
                msgQueue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ChatMessage next() throws RemoteException {
        return msgQueue.poll();
    }
}
