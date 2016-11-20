package org.pyn.message;

import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Created by pyn on 2016/11/18.
 */
public class FriendsResponse extends Response{
    private LinkedList<String> friendQueue;

    public FriendsResponse() {
        this.type = "FriendsResponse";
    }

    public LinkedList<String> getFriendQueue() {
        return friendQueue;
    }

    public void setFriendQueue(LinkedList<String> friendQueue) {
        this.friendQueue = friendQueue;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("friendQueue", friendQueue);
        return jsonObject.toString().getBytes();
    }
}
