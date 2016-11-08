package org.pyn;
import java.net.Socket;
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

    public Connection(Socket socket, Map<String,Socket> nameSocketTable) {
        this.queue = new RequestQueue();
        this.socket = socket;
        this.nameSocketTable = nameSocketTable;
    }

    public void start() {
        recv = new ClientReceive(socket, queue);
        send = new ClientSend(socket, queue,nameSocketTable);
        Thread crtThread = new Thread(recv);
        Thread cstThread = new Thread(send);
        crtThread.start();
        cstThread.start();
    }

}
