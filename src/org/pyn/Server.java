package org.pyn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by pyn on 2016/10/21.
 */

public class Server {
    private Connection conn;
    private Map<String,Socket> nameSocketTable;
    private Map<String, LinkedList<String> > friendsTable;
    public Server() {
        this.nameSocketTable = new HashMap<String,Socket>();
        this.friendsTable = new HashMap<String, LinkedList<String> >();
    }

    public void serverStart() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8090);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {
            try {
                Socket socket = null;
                if (ss != null) {
                    socket = ss.accept();
                }
                conn = new Connection(socket,nameSocketTable,friendsTable);
                conn.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
