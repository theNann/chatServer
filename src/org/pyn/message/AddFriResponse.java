package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/3.
 */
public class AddFriResponse extends Response {
    private String result;

    public AddFriResponse() {
        this.type = "AddFriResponse";
    }

    public AddFriResponse(String result) {
        this.result = result;
        this.type = "AddFriResponse";
    }

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("result", result);
        return jsonObject.toString().getBytes();
    }

}
