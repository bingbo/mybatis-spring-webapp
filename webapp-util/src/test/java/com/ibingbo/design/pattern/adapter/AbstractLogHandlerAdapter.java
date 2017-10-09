package com.ibingbo.design.pattern.adapter;

import java.util.List;

/**
 * AbstractLogHandlerAdapter
 *
 * @author zhangbingbing
 * @date 2017/9/26
 */
public abstract class AbstractLogHandlerAdapter implements LogHandlerAdapter {

    public List<Object> handle(Object handler) {
        return this.handleInternal((LogObject) handler);
    }

    public boolean supports(Object handler) {
        return handler instanceof LogObject && this.supportsInternal((LogObject) handler);
    }

    protected abstract boolean supportsInternal(LogObject optLogEvent);

    protected abstract List<Object> handleInternal(LogObject handler);

}
