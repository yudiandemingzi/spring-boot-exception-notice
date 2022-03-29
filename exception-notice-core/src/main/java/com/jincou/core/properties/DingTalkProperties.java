package com.jincou.core.properties;

import com.jincou.core.enums.DingTalkMsgTypeEnum;
import lombok.Data;

import static com.jincou.core.enums.DingTalkMsgTypeEnum.TEXT;


/**
 *  钉钉机器人配置
 *
 * @author xub
 * @date 2022/3/29 下午4:58
 */
@Data
public class DingTalkProperties {

    /**
     * 钉钉机器人webHook地址
     */
    private String webHook;

    /**
     * 发送消息时被@的钉钉用户手机号
     */
    private String[] atMobiles;

    /**
     * 发送消息时被@的钉钉用户手机号
     */
    private Boolean isAtAll = false;

    /**
     * 消息类型 暂只支持text和markdown
     */
    private DingTalkMsgTypeEnum msgType = TEXT;
}
