package com.jincou.core.config;


import com.jincou.core.aop.ExceptionListener;
import com.jincou.core.handler.ExceptionNoticeHandler;
import com.jincou.core.process.DingTalkNoticeProcessor;
import com.jincou.core.process.INoticeProcessor;
import com.jincou.core.process.MailNoticeProcessor;
import com.jincou.core.process.WeChatNoticeProcessor;
import com.jincou.core.properties.DingTalkProperties;
import com.jincou.core.properties.ExceptionNoticeProperties;
import com.jincou.core.properties.MailProperties;
import com.jincou.core.properties.WeChatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 *  异常信息通知配置类
 *
 * @author xub
 * @date 2022/3/30 下午3:01
 */
@Configuration
@ConditionalOnProperty(prefix = ExceptionNoticeProperties.PREFIX, name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = ExceptionNoticeProperties.class)
public class ExceptionNoticeAutoConfiguration {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired(required = false)
    private MailSender mailSender;

    /**
     * 注入 异常处理bean
     */
    @Bean(initMethod = "start")
    public ExceptionNoticeHandler nticeHandler(ExceptionNoticeProperties properties) {
        List<INoticeProcessor> noticeProcessors = new ArrayList<>(2);
        INoticeProcessor noticeProcessor;
        DingTalkProperties dingTalkProperties = properties.getDingTalk();
        if (null != dingTalkProperties) {
            noticeProcessor = new DingTalkNoticeProcessor(restTemplate, dingTalkProperties);
            noticeProcessors.add(noticeProcessor);
        }
        WeChatProperties weChatProperties = properties.getWeChat();
        if (null != weChatProperties) {
            noticeProcessor = new WeChatNoticeProcessor(restTemplate, weChatProperties);
            noticeProcessors.add(noticeProcessor);
        }

        MailProperties email = properties.getMail();
        if (null != email && null != mailSender) {
            noticeProcessor = new MailNoticeProcessor(mailSender, email);
            noticeProcessors.add(noticeProcessor);
        }

        Assert.isTrue(noticeProcessors.size() != 0, "Exception notification configuration is incorrect");
        return new ExceptionNoticeHandler(properties, noticeProcessors);
    }

    /**
     * 注入异常捕获aop
     */
    @Bean
    @ConditionalOnClass(ExceptionNoticeHandler.class)
    public ExceptionListener exceptionListener(ExceptionNoticeHandler nticeHandler) {
        return new ExceptionListener(nticeHandler);
    }
}
