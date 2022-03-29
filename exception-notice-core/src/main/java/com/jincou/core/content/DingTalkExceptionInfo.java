package com.jincou.core.content;


import com.jincou.core.enums.DingTalkMsgTypeEnum;
import com.jincou.core.properties.DingTalkProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.jincou.core.enums.DingTalkMsgTypeEnum.TEXT;
import static com.jincou.core.enums.DingTalkMsgTypeEnum.MARKDOWN;


/**
 *  钉钉异常通知消息请求体
 *
 * @author xub
 * @date 2022/3/29 下午4:56
 */
@Data
public class DingTalkExceptionInfo {

    private String msgtype;

    private DingDingText text;

    private DingDingMarkDown markdown;

    private DingDingAt at;

    public DingTalkExceptionInfo(ExceptionInfo exceptionInfo, DingTalkProperties dingTalkProperties) {
        DingTalkMsgTypeEnum msgType = dingTalkProperties.getMsgType();
        if (msgType.equals(TEXT)) {
            this.text = new DingDingText(exceptionInfo.createText());
        } else if (msgType.equals(MARKDOWN)) {
            this.markdown = new DingDingMarkDown(exceptionInfo.getProject(), exceptionInfo.createDingTalkMarkDown());
        }
        this.msgtype = msgType.getMsgType();
        this.at = new DingDingAt(dingTalkProperties.getAtMobiles(), dingTalkProperties.getIsAtAll());
    }

    @AllArgsConstructor
    @Data
    static class DingDingText {

        private String content;

    }

    @AllArgsConstructor
    @Data
    static class DingDingMarkDown {

        private String title;

        private String text;

    }

    @AllArgsConstructor
    @Data
    static class DingDingAt {

        private String[] atMobiles;

        private boolean isAtAll;

    }


}
