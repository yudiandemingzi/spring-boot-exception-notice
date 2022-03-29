package com.jincou.core.process;


import com.jincou.core.content.ExceptionInfo;


/**
 *  异常信息通知处理接口
 *
 * @author xub
 * @date 2022/3/29 下午4:23
 */
@FunctionalInterface
public interface INoticeProcessor {

    /**
     * 异常信息通知
     *
     * @param exceptionInfo 异常信息
     */
    void sendNotice(ExceptionInfo exceptionInfo);

}
