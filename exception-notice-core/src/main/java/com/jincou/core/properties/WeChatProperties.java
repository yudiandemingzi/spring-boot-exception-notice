package com.jincou.core.properties;

import com.jincou.core.enums.WeChatMsgTypeEnum;
import lombok.Data;

import static com.jincou.core.enums.WeChatMsgTypeEnum.TEXT;


/**
 *  企业微信机器人配置
 *
 * @author xub
 * @date 2022/3/29 下午4:17
 */
@Data
public class WeChatProperties {

    /**
     * userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userid，可以使用atPhones
     */
    private String[] atUserIds;

    /**
     * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
     */
    private String[] atPhones;

    /**
     * 企业微信机器人webHook地址
     */
    private String webHook;

    /**
     * 消息类型 暂只支持text和markdown
     */
    private WeChatMsgTypeEnum msgType = TEXT;
}
