package com.jincou.core.process;


import com.jincou.core.content.ExceptionInfo;
import com.jincou.core.content.WeChatExceptionInfo;
import com.jincou.core.properties.WeChatProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 *  企业微信异常信息通知具体实现
 *
 * @author xub
 * @date 2022/3/29 下午4:15
 */
public class WeChatNoticeProcessor implements INoticeProcessor {

    private final WeChatProperties weChatProperties;

    private final RestTemplate restTemplate;

    private final Log logger = LogFactory.getLog(getClass());

    public WeChatNoticeProcessor(RestTemplate restTemplate,
                                 WeChatProperties weChatProperties) {
        Assert.hasText(weChatProperties.getWebHook(), "WeChat webHook must not be null");
        this.weChatProperties = weChatProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendNotice(ExceptionInfo exceptionInfo) {
        WeChatExceptionInfo weChatNoticeProcessor = new WeChatExceptionInfo(exceptionInfo, weChatProperties);
        String result = restTemplate.postForObject(weChatProperties.getWebHook(), weChatNoticeProcessor, String.class);
        logger.debug(result);
    }

}
