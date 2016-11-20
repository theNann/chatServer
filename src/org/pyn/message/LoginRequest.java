package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/1.
 */
public class LoginRequest extends Request{
    private String name;

    public LoginRequest() {
        this.type = "LoginRequest";
    }

    public LoginRequest(String name) {
        this.type = "LoginRequest";
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "name='" + name + '\'' +
                ", type=" + type + "}";
    }

    @Override
    public void decode(JSONObject jsonObject) {
        setName((String)jsonObject.get("name"));
    }
}
