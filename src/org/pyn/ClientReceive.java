package org.pyn;

import org.pyn.message.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;


/**
 * Created by pyn on 2016/10/20.
 */

public class ClientReceive implements Runnable{
    private Socket socket;
    private Proto proto;
    private RequestQueue queue;

    public ClientReceive(Socket socket, RequestQueue queue) {
        this.socket = socket;
        this.proto = new Proto();
        this.queue = queue;
    }

    @Override
    public void run() {
        boolean ok = true;
        Request req = null;
        while(ok) {
            try {
                req = doReceiveMsg();
                queue.push(req);
                System.out.println("ClientReceive : " + req);
            } catch (IOException e) {
                ok = false;
            }
        }
    }

    private Request doReceiveMsg() throws IOException {
        InputStream input = socket.getInputStream();
        byte[] in = new byte[65538];
        int len = 0;
        int curlen = 0;

        while(curlen < 2) {
            curlen += input.read(in, curlen, 2 - curlen);
        }

        len = byteToNum(in);
        curlen = 0;
        while(curlen < len) {
            curlen += input.read(in,curlen+2,len - curlen);
        }

        Request req = null;
        try {
            req = proto.decode(in, len);
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return req;
    }

    private int byteToNum(byte[] b) {
        int len = 0;
        len = (int)b[1] << 8 | b[0];
        return len;
    }
}
