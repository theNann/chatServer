package org.pyn.message;

import org.json.JSONObject;
import org.pyn.RequestQueue;

/**
 * Created by pyn on 2016/11/18.
 */
public class FriendsRequest extends Request{
    public FriendsRequest() {
        this.type = "FriendsRequest";
    }

    @Override
    public String toString() {
        return "FriendsRequest{}, type=" + type;
    }

    @Override
    public void decode(JSONObject jsonObject) {

    }
}
