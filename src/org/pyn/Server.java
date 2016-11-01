package org.pyn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pyn on 2016/10/21.
 */
public class Server {
    private Connection conn;

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
                conn = new Connection(socket);
                conn.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
