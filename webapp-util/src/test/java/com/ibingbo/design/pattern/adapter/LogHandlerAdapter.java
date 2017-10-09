package com.ibingbo.design.pattern.adapter;

/**
 * LogHandlerAdapter
 *
 * @author zhangbingbing
 * @date 2017/9/26
 */
public interface LogHandlerAdapter {
    boolean supports(Object handler);

    Object handle(Object handler);

}
