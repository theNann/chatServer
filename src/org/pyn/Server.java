package org.pyn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pyn on 2016/10/21.
 */

public class Server {
    private Connection conn;
    private Map<String,Socket> nameSocketTable;

    public Server() {
       this.nameSocketTable = new HashMap<String,Socket>();
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
                conn = new Connection(socket,nameSocketTable);
                conn.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
