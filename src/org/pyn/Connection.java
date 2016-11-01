package org.pyn;

import org.pyn.message.Request;

import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by pyn on 2016/11/1.
 */
public class Connection {

    private LinkedList<Request> queue = new LinkedList<Request>();
    private Socket socket;
    private ClientReceive recv;
    private ClientSend send;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        recv = new ClientReceive(socket, queue);
        send = new ClientSend(socket, queue);
        Thread crtThread = new Thread(recv);
        Thread cstThread = new Thread(send);
        crtThread.start();
        cstThread.start();
    }

}
