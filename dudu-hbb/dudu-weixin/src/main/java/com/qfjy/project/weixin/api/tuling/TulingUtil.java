package com.qfjy.project.weixin.api.tuling;

import com.qfjy.project.weixin.api.tuling.bean.InputText;
import com.qfjy.project.weixin.api.tuling.bean.Perception;
import com.qfjy.project.weixin.api.tuling.bean.TulingBean;
import com.qfjy.project.weixin.api.tuling.bean.UserInfo;
import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 贺兵兵
 * @Date: 2019/11/21
 * @Time: 11:20
 */
@Service
public class TulingUtil {
    private static final String TULING_API_URL="http://openapi.tuling123.com/openapi/api/v2";
    private static final String APIKEY_1="89da5a52916b41b3a6b6f044e62e874b";
    private static final String APIKEY_2="ac567b40292f4737878d4cccb4cb4ba9";
    private static final String APIKEY_3="f644385edee043948921333dd994e1ce";
    private static final String APIKEY_4="9519f71257d64c4fbc2c4f614dfeef22";
    private static final String APIKEY_5="a945b9176b4b45558e4bef4754336dc2";
    public String sendMessage(String msg,int max){
         String[] strings = {APIKEY_1,APIKEY_2,APIKEY_3,APIKEY_4,APIKEY_5};

        JSONObject jsonObject = sendJSONObject(msg, strings[max]);
        JSONObject object = WeixinUtil.httpRequest(TULING_API_URL, "POST", jsonObject.toString());
       // System.out.println("响应的消息: "+object.toString());
        JSONArray array = (JSONArray) object.get("results");
        JSONObject object1= (JSONObject) array.get(0);
        JSONObject object2= (JSONObject) object1.get("values");
        String result= (String) object2.get("text");
        if (result.equals("请求次数超限制!")){
             max+=1;
            if (max>4){
                max=0;
                return "所有机器人次数用完了";
            }
           return sendMessage(msg,max);
        }
        return result;
    }
    public JSONObject sendJSONObject(String msg,String apiKey){
        InputText inputText = new InputText();
        inputText.setText(msg);
        Perception perception = new Perception();
        perception.setInputText(inputText);

        UserInfo userInfo = new UserInfo();
        userInfo.setApiKey(apiKey);
        userInfo.setUserId("java1903");

        TulingBean tulingBean = new TulingBean();
        tulingBean.setPerception(perception);
        tulingBean.setUserInfo(userInfo);

        JSONObject jsonObject = JSONObject.fromObject(tulingBean);

        return jsonObject;
    }
}
