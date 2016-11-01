package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/1.
 */
public class LoginResponse extends Response{
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject.toString().getBytes();
    }
}
