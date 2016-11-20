package org.pyn.message;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/1.
 */
public class ChatResponse extends Response{
    private String fromName;
    private String content;
    private boolean success;
    public ChatResponse() {
        this.type = "ChatResponse";
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("success",success);
        jsonObject.put("fromName", fromName);
        jsonObject.put("content", content);
        return jsonObject.toString().getBytes();
    }
}
