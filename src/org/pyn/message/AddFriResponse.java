package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/3.
 */
public class AddFriResponse extends Response {
    private String result;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("result", result);
        jsonObject.put("name",name);
        return jsonObject.toString().getBytes();
    }

}
