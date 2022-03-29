package com.jincou.core.properties;

import lombok.Data;

/**
 *  邮箱配置
 *
 * @author xub
 * @date 2022/3/29 下午4:17
 */
@Data
public class MailProperties {

    /**
     * 发送人
     */
    private String from;
    /**
     * 接收人，可多选
     */
    private String[] to;
    /**
     * 抄送人，可多选
     */
    private String[] cc;

}
