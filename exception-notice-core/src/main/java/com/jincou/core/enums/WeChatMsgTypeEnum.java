package com.jincou.core.enums;

/**
 *  企业微信文本类型枚举
 *
 * @author xub
 * @date 2022/3/29 下午4:44
 */
public enum WeChatMsgTypeEnum {

    TEXT("text"), MARKDOWN("markdown");

    private final String msgType;

    public String getMsgType() {
        return msgType;
    }

    WeChatMsgTypeEnum(String msgType) {
        this.msgType = msgType;
    }
}
