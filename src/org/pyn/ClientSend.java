package org.pyn;

import org.pyn.message.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by pyn on 2016/10/29.
 */
public class ClientSend implements Runnable{
    private Socket socket;
    private String cur_name;
    private Proto proto;
    private RequestQueue queue;
    private Map<String,Socket> nameSocketTable;
    private Map<String,LinkedList<String>> friendsTable;

    public ClientSend(Socket socket, RequestQueue queue, Map<String,Socket> nameSocketTable, Map<String,LinkedList<String> > friendsTable) {
        this.socket = socket;
        this.proto = new Proto();
        this.queue = queue;
        this.nameSocketTable = nameSocketTable;
        this.friendsTable = friendsTable;
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
        boolean containkey;
        if(req.type.equals("LoginRequest") ) {
            LoginRequest loginRequest = (LoginRequest)req;
            cur_name = loginRequest.getName();
            LoginResponse loginResp = new LoginResponse();
            containkey = nameSocketTable.containsKey(cur_name);

            if(containkey) {
                loginResp.setResult(cur_name + "has existed");
            } else {
                loginResp.setResult("OK");
                nameSocketTable.put(cur_name,socket);
                friendsTable.put(cur_name,new LinkedList<String>());
            }
            output = socket.getOutputStream();
            byte[] b = proto.encode(loginResp);
            output.write(b);
        } else if(req.type.equals("ChatRequest") ) {
            ChatRequest chatRequest = (ChatRequest)req;
            String toName = chatRequest.getToName();
            String content = chatRequest.getContent();
            ChatResponse chatResponse = new ChatResponse();

            containkey = nameSocketTable.containsKey(toName);
            if(containkey) {
                chatResponse.setSuccess(true);
                chatResponse.setFromName(cur_name);
                chatResponse.setContent(content);
                Socket socket_tmp = nameSocketTable.get(toName);
                output = socket_tmp.getOutputStream();
                output.write(proto.encode(chatResponse));
            } else {
                chatResponse.setSuccess(false);
                chatResponse.setFromName("null");
                chatResponse.setContent(toName + "不在线");
                output = socket.getOutputStream();
                output.write(proto.encode(chatResponse));
            }
        } else if(req.type.equals("AddFriRequest")) {
            AddFriRequest addFriRequest = (AddFriRequest) req;
            AddFriResponse addFriResponse = new AddFriResponse();
            String friend_name = addFriRequest.getName();
            containkey = nameSocketTable.containsKey(friend_name);

            if(containkey) {
                addFriResponse.setResult("on-yes");
                friendsTable.get(cur_name).add(friend_name);
                friendsTable.get(friend_name).add(cur_name);
            } else {
                addFriResponse.setResult("off");
            }
            addFriResponse.setName(addFriRequest.getName());
            output = socket.getOutputStream();
            output.write(proto.encode(addFriResponse));
        } else if(req.type.equals("FriendsRequest")) {
            FriendsResponse friendsResponse = new FriendsResponse();
            friendsResponse.setFriendQueue(friendsTable.get(cur_name));
            output = socket.getOutputStream();
            output.write(proto.encode(friendsResponse));
        }
    }
}

