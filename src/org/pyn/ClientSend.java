package org.pyn;

import org.pyn.message.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

/**
 * Created by pyn on 2016/10/29.
 */
public class ClientSend implements Runnable{
    private Socket socket;
    private Proto proto;
    private RequestQueue queue;
    private Map<String,Socket> nameSocketTable;

    public ClientSend(Socket socket, RequestQueue queue, Map<String,Socket> nameSocketTable) {
        this.socket = socket;
        this.proto = new Proto();
        this.queue = queue;
        this.nameSocketTable = nameSocketTable;
    }

    @Override
    public void run() {
        boolean ok = true;
        Request req = null;

        while (ok) {
            try {
                req = queue.pop();
                ////////////////////////////
                System.out.println("Clientsend : " + req);
                /////////////////////////////////
                doSendMsg(req);
            } catch (IOException e) {
                ok = false;
            }
        }
    }


    private void doSendMsg(Request req) throws IOException {
        OutputStream output;
        if(req.type.equals("LoginRequest") ) {
            boolean containKey;
            LoginRequest loginRequest = (LoginRequest)req;
            LoginResponse loginResp = new LoginResponse();
            containKey = nameSocketTable.containsKey(loginRequest.getName());
            if(containKey) {
                loginResp.setResult(loginRequest.getName() + "has existed");
            } else {
                loginResp.setResult("OK");
                nameSocketTable.put(loginRequest.getName(),socket);
            }
            output = socket.getOutputStream();
            byte[] b = proto.encode(loginResp);
            output.write(b);
        } else if(req.type.equals("ChatRequest") ) {
            ChatRequest chatRequest = (ChatRequest)req;
            ChatResponse chatResp = new ChatResponse();
        } else if(req.type.equals("AddFriRequest")) {
            AddFriRequest addFriRequest = (AddFriRequest) req;
            AddFriResponse addFriResponse = new AddFriResponse();
            boolean containkey = nameSocketTable.containsKey(addFriRequest.getName());
            if(containkey) {
                addFriResponse.setResult("Fail");
            } else {
                addFriResponse.setResult("OK");
            }
            output = socket.getOutputStream();
            output.write(proto.encode(addFriResponse));
        }
    }
}

