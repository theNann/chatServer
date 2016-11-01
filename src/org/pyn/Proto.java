package org.pyn;

import org.json.JSONObject;
import org.pyn.message.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by pyn on 2016/10/21.
 */
public class Proto {

    public byte[] encode(Response resp) {
        byte[] str_b = resp.encode();
        int len = str_b.length;
        byte[] buffer = new byte[len+2];
        byte[] len_b = numToByte(len);
        buffer[0] = len_b[0];
        buffer[1] = len_b[1];

        for(int i = 0; i < len; i++) {
            buffer[i+2] = str_b[i];
        }
        return buffer;
    }

    public Request decode(byte[] b, int len) throws UnsupportedEncodingException {
        String s = new String(b, 2, len, "UTF-8");
        JSONObject jsonObject = new JSONObject(s);
        String type = (String)jsonObject.get("type");
        if(type.compareTo("LoginRequest") == 0) {
            return new LoginRequest((String)jsonObject.get("name"));
        } else if(type.compareTo("ChatRequest") == 0) {
            ChatRequest cr = new ChatRequest();
            //return cr;
        }
        return null;
    }

    private byte[] numToByte(int num) {
        byte[] b = new byte[2];
        b[0] = (byte) (num & 255);
        b[1] = (byte) (num >> 8 & 255);
        return b;
    }
}
