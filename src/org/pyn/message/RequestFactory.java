package org.pyn.message;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pyn on 2016/11/20.
 */
public class RequestFactory {
    private interface newRequest {
        Request getRequest();
    }
    private Map<String,newRequest> typeRequest = new HashMap<String,newRequest>();

    public RequestFactory() {
        typeRequest.put("LoginRequest", new newRequest() {
            @Override
            public Request getRequest() {
                return new LoginRequest();
            }
        });
        typeRequest.put("ChatRequest", new newRequest() {
            @Override
            public Request getRequest() {
                return new ChatRequest();
            }
        });
        typeRequest.put("AddFriRequest", new newRequest() {
            @Override
            public Request getRequest() {
                return new AddFriRequest();
            }
        });
        typeRequest.put("FriendsRequest", new newRequest() {
            @Override
            public Request getRequest() {
                return new FriendsRequest();
            }
        });
    }

    public Request decode(String type,JSONObject jsonObject) {
        Request request = typeRequest.get(type).getRequest();
        request.decode(jsonObject);
        return request;
    }

}
