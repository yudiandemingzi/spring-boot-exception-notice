package com.jincou.core.content;


import com.jincou.core.enums.WeChatMsgTypeEnum;
import com.jincou.core.properties.WeChatProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.jincou.core.enums.WeChatMsgTypeEnum.MARKDOWN;
import static com.jincou.core.enums.WeChatMsgTypeEnum.TEXT;


/**
 *  企业微信异常通知消息请求体
 *
 * @author xub
 * @date 2022/3/29 下午4:44
 */
@Data
public class WeChatExceptionInfo {

    private WeChatText text;
    private WeChatMarkDown markdown;
    private String msgtype;

    public WeChatExceptionInfo(ExceptionInfo exceptionInfo, WeChatProperties weChatProperties) {
        WeChatMsgTypeEnum msgType = weChatProperties.getMsgType();
        if (msgType.equals(TEXT)) {
            this.text = new WeChatText(exceptionInfo.createText(), weChatProperties.getAtUserIds(), weChatProperties.getAtPhones());
        } else if (msgType.equals(MARKDOWN)) {
            this.markdown = new WeChatMarkDown(exceptionInfo.createWeChatMarkDown());
        }
        this.msgtype = msgType.getMsgType();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class WeChatText {

        private String content;

        private String[] mentioned_list;

        private String[] mentioned_mobile_list;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class WeChatMarkDown {

        private String content;

    }


}
