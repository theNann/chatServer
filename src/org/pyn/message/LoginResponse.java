package org.pyn.message;


import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/1.
 */
public class LoginResponse extends Response{
    private String result;

    public LoginResponse() {
        this.type = "LoginResponse";
    }
    public LoginResponse(String result) {
        this.result = result;
        this.type = "LoginResponse";
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "result='" + result + '\'' +
                '}';
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",type);
        jsonObject.put("result",result);
        return jsonObject.toString().getBytes();
    }
}
