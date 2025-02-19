package datafocus.ai.mcp.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author sunc
 * @date 2025/2/19 10:14
 * @description GptText2DataInitParam
 */
@Data
public class GptText2DataInitParam {

    private String language;
    private List<String> names;

    public static String buildBody(List<String> names, String language) {
        GptText2DataInitParam param = new GptText2DataInitParam();
        param.language = language;
        param.names = names;
        return JSONObject.toJSONString(param);
    }

}
