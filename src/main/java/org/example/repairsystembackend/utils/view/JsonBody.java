package org.example.repairsystembackend.utils.view;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.repairsystembackend.utils.json.JsonHelper;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class JsonBody {
    // 状态码
    private int code;

    // 请求结果状态
    private boolean success;

    // 请求操作描述
    private String description;

    // 响应数据结构
    private Map<String, Object> context;

    private JsonBody(int code, boolean success, String description) {
        this.code = code;
        this.success = success;
        this.description = description;
    }

    private JsonBody() {
        this(0, false, "");
    }

    public static JsonBody success(int code, String description) {
        return new JsonBody(code, true, description);
    }

    public static JsonBody failed(int code, String description) {
        return new JsonBody(code, false, description);
    }

    public void toClient(HttpServletResponse response) {
        JsonHelper.responseToClient(response, this);
    }

    public JsonBody add(String key, Object value) {
        if (context == null) {
            context = new HashMap<>();
        }
        context.put(key, value);
        return this;
    }

}
