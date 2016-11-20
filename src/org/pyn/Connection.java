package org.pyn;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Map;
/**
 * Created by pyn on 2016/11/1.
 */
public class Connection {
    private RequestQueue queue;
    private Socket socket;
    private ClientReceive recv;
    private ClientSend send;
    private Map<String,Socket> nameSocketTable;
    private Map<String,LinkedList<String> > friendsTable;

    public Connection(Socket socket, Map<String,Socket> nameSocketTable, Map<String,LinkedList<String> > friendsTable) {
        this.queue = new RequestQueue();
        this.socket = socket;
        this.nameSocketTable = nameSocketTable;
        this.friendsTable = friendsTable;
    }

    public void start() {
        recv = new ClientReceive(socket, queue);
        send = new ClientSend(socket, queue,nameSocketTable,friendsTable);
        Thread crtThread = new Thread(recv);
        Thread cstThread = new Thread(send);
        crtThread.start();
        cstThread.start();
    }

}
