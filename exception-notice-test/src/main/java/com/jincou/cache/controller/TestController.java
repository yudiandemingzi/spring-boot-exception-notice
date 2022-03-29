package com.jincou.cache.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试
 *
 * @author xub
 * @date 2022/3/16 下午3:13
 */
@Slf4j
@RestController
public class TestController {


    @RequestMapping(value = "/queryUser")
    public void queryUser(@RequestParam("userId") String userId) throws IllegalAccessException {

        throw new IllegalAccessException("监控报警: 用户不存在id=" + userId);

    }


}
