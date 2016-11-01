package org.pyn;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * Created by pyn on 2016/10/29.
 */
public class ClientSend implements Runnable{
    private Socket socket;
    private Proto proto;

    public ClientSend(Socket socket) {
        this.socket = socket;
        proto = new Proto();
    }
    @Override
    public void run() {

    }

    private Message doReceiveMsg(Socket socket) throws IOException {
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

        Message m = null;
        try {
            m = proto.decode(in, len);
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return m;
    }

    private int byteToNum(byte[] b) {
        int len = 0;
        len = (int)b[1] << 8 | b[0];
        return len;
    }
}

