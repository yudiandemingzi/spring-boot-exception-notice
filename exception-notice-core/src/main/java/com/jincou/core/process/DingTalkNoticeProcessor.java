package com.jincou.core.process;


import com.jincou.core.content.ExceptionInfo;
import com.jincou.core.content.DingTalkExceptionInfo;
import com.jincou.core.content.DingTalkResult;
import com.jincou.core.properties.DingTalkProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 *  钉钉异常信息通知具体实现
 *
 * @author xub
 * @date 2022/3/29 下午4:16
 */
@Slf4j
public class DingTalkNoticeProcessor implements INoticeProcessor {

    private final DingTalkProperties dingTalkProperties;

    private final RestTemplate restTemplate;

    public DingTalkNoticeProcessor(RestTemplate restTemplate,
                                   DingTalkProperties dingTalkProperties) {
        Assert.hasText(dingTalkProperties.getWebHook(), "DingTalk webHook must not be null");
        this.dingTalkProperties = dingTalkProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendNotice(ExceptionInfo exceptionInfo) {
        DingTalkExceptionInfo dingDingNotice = new DingTalkExceptionInfo(exceptionInfo,
                dingTalkProperties);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DingTalkExceptionInfo> entity = new HttpEntity<>(dingDingNotice, headers);
        DingTalkResult result = restTemplate.postForObject(dingTalkProperties.getWebHook(), entity, DingTalkResult.class);
        log.debug(String.valueOf(result));
    }

}
