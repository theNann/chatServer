package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/11/1.
 */
public class ChatRequest extends Request{
    private String toName;
    private String content;

    public ChatRequest() {
        this.type = "ChatRequest";
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChatRequest{" +
                "to_name='" + toName + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public void decode(JSONObject jsonObject) {
        setToName((String)jsonObject.get("toName"));
        setContent((String)jsonObject.get("content"));
    }
}
