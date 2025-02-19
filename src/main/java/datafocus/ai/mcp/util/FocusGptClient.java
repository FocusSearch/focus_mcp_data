package datafocus.ai.mcp.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;

/**
 * @author sunc
 * @date 2025/2/19 10:15
 * @description FocusGptClient
 */
public class FocusGptClient {

    private static final String BASE_URL = "https://cloud001.datafocus.ai";

    private static final String GPT_TABLE_LIST_URI = "/df/rest/table/list";
    private static final String GPT_TO_DATA_INIT_URI = "/df/rest/gpt/init";
    private static final String GPT_TO_DATA_DATA_URI = "/df/rest/gpt/data";

    public static String getTableList(String name, String bearer) {
        String url = BASE_URL + GPT_TABLE_LIST_URI;
        if (name != null) {
            url = url + "?name=" + name;
        }
        HttpRequest request = HttpUtil.createGet(url);
        request.header("Authorization", StrUtil.format("Bearer {}", bearer));
        HttpResponse response = request.execute();

        if (response.getStatus() == HttpStatus.HTTP_OK) {
            return response.body();
        }
        return "";
    }

    /**
     * GPT 自然语言查询数据:初始化对话
     */
    public static String gptText2DataInit(String body, String bearer) {
        return gptText2DataExecute(GPT_TO_DATA_INIT_URI, body, bearer);
    }

    /**
     * GPT 自然语言查询数据:查询数据结果
     */
    public static String gptText2DataData(String body, String bearer) {
        return gptText2DataExecute(GPT_TO_DATA_DATA_URI, body, bearer);
    }

    private static String gptText2DataExecute(String uri, String body, String bearer) {
        String url = BASE_URL + uri;
        HttpRequest request = HttpUtil.createPost(url);
        request.header("Authorization", StrUtil.format("Bearer {}", bearer)).body(body);
        HttpResponse response = request.execute();

        if (response.getStatus() == HttpStatus.HTTP_OK) {
            return response.body();
        }
        return "";
    }

}
