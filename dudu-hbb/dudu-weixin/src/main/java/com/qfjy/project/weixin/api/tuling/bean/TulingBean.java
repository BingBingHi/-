package com.qfjy.project.weixin.api.tuling.bean;

import lombok.Data;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 贺兵兵
 * @Date: 2019/11/21
 * @Time: 11:25
 */
@Data
public class TulingBean {
    private int reqType=0;
    private Perception perception;
    private UserInfo userInfo;
}
