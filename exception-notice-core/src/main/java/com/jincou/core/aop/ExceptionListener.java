package com.jincou.core.aop;

import com.jincou.core.handler.ExceptionNoticeHandler;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;


/**
 *  异常捕获切面
 *
 * @author xub
 * @date 2022/3/29 下午2:59
 */
@Aspect
@RequiredArgsConstructor
public class ExceptionListener {

    private final ExceptionNoticeHandler handler;

    @AfterThrowing(value = "@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller) || @within(com.jincou.core.aop.ExceptionNotice)", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Exception e) {
        handler.createNotice(e, joinPoint);
    }
}


/**
 * Spring 依赖注入方式主要有 2 种，
 * 1、通过 @Autowire、@Resource 等注解注入。
 * 2、二是通过构造器的方式进行依赖注入。
 * 除此之外，其实 lombok 的 @RequiredArgsConstructor 注解也可以完成 spring 的依赖注入，且更简便，更灵活。
 *
 * 使用方式: 类上加@RequiredArgsConstructor,属性加final修饰。
 */


/**
 * @AfterThrowing: 在方法抛出异常退出时执行的通知
 *
 * @within: @within(注解类型全限定名)匹配所有持有指定注解的类里面的方法, 即要把注解加在类上. 在接口上声明不起作用 。
 */