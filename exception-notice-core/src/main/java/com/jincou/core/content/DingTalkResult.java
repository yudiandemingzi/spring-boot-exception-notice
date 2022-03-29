package com.jincou.core.content;

import lombok.Data;

/**
 *  钉钉异常通知响应结果
 *
 * @author xub
 * @date 2022/3/29 下午4:57
 */
@Data
public class DingTalkResult {

    private int errcode;

    private String errmsg;

    @Override
    public String toString() {
        return "DingDingResult [errcode=" + errcode + ", errmsg=" + errmsg + "]";
    }

}
