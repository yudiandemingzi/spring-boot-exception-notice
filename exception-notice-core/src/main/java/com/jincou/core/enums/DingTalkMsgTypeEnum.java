package com.jincou.core.enums;

/**
 *  钉钉文本类型枚举
 *
 * @author xub
 * @date 2022/3/29 下午4:44
 */
public enum DingTalkMsgTypeEnum {

    TEXT("text"), MARKDOWN("markdown");

    private final String msgType;

    public String getMsgType() {
        return msgType;
    }

    DingTalkMsgTypeEnum(String msgType) {
        this.msgType = msgType;
    }
}
