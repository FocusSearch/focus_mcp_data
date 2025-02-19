package datafocus.ai.mcp.server;

import datafocus.ai.mcp.entity.GptText2DataInitParam;
import datafocus.ai.mcp.entity.GptText2DataChatParam;
import datafocus.ai.mcp.util.FocusGptClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunc
 * @date 2025/2/19 10:14
 * @description FocusGptService
 */
@Service
public class FocusGptService {

    /**
     * 获取系统中已导入的表列表
     *
     * @param name   表名查询参数
     * @param bearer bearer token
     */
    @Tool(description = "获取系统中已导入的表列表")
    public String tableList(@ToolParam(description = "表名查询参数", required = false) String name,
                               @ToolParam(description = "鉴权token") String bearer) {
        return FocusGptClient.getTableList(name, bearer);
    }

    /**
     * GPT 自然语言查询数据:初始化对话
     *
     * @param names    表列表
     * @param language 语言
     * @param bearer   bearer token
     */
    @Tool(description = "自然语言查询数据:初始化对话")
    public String gptText2DataInit(@ToolParam(description = "表列表") List<String> names,
                                   @ToolParam(description = "上下文语言, 取值为 english 或者 chinese, 默认是 chinese") String language,
                                   @ToolParam(description = "鉴权token") String bearer) {
        String body = GptText2DataInitParam.buildBody(names, language);
        return FocusGptClient.gptText2DataInit(body, bearer);
    }

    /**
     * GPT 自然语言查询数据:查询数据结果
     *
     * @param chatId 初始化对话的对话id
     * @param input  用户输入
     * @param bearer bearer token
     */
    @Tool(description = "自然语言查询数据:查询数据结果")
    public String gptText2DataData(@ToolParam(description = "初始化对话返回的对话id") String chatId,
                                   @ToolParam(description = "需要转换为sql的自然语言") String input,
                                   @ToolParam(description = "鉴权token") String bearer) {
        String body = GptText2DataChatParam.buildBody(chatId, input);
        return FocusGptClient.gptText2DataData(body, bearer);
    }

}
