package org.pyn;

import org.pyn.message.Request;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by pyn on 2016/10/20.
 */

public class ClientReceive implements Runnable{
    private Socket socket;
    private Proto proto;
    public ClientReceive(Socket socket) {
        this.socket = socket;
        this.proto = new Proto();
        receiveQue.clear();
    }

    @Override
    public void run() {
        while(true) {
            if(receiveQue.isEmpty()) {
                continue;
            }
            MsgElement msgElement = receiveQue.pop();
//            Socket from = msgElement.getFrom();
            Message msg = msgElement.getMsg();
//            try {
//                msg = doReceiveMsg(from);
//            } catch (IOException e) {
//                try {
//                    socket.getInputStream().close();
//                    socket.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//                break;
//            }
            try {
                doSendMsg(msg);
            } catch (IOException e) {
                try {
                    socket.getInputStream().close();
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            }
        }
    }

    private void doSendMsg(Message msg) throws IOException {
        OutputStream output = socket.getOutputStream();
        byte[] b = proto.encode(msg);
        output.write(b);
    }
}
