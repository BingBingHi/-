package com.qfjy.project.weixin.api.hitokoto;

import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 贺兵兵
 * @Date: 2019/11/21
 * @Time: 23:30
 */
@Service
public class HikotoUtil {
    private static final String HITOKO_API_URL="https://v1.hitokoto.cn/";
    public String sendMessage(){
        JSONObject jsonObject = WeixinUtil.httpRequest(HITOKO_API_URL, "GET", null);
        String result = (String) jsonObject.get("hitokoto");
        return result;
    }
}
