package com.ibingbo.design.pattern.adapter;

import java.util.List;

/**
 * GroupOptLogAddHandlerAdapter
 *
 * @author zhangbingbing
 * @date 2017/9/26
 */
public class ALogHandlerAdapter extends AbstractLogHandlerAdapter {

    protected List<Object> handleInternal(LogObject handler) {
        LogObject event = (LogObject) handler;
        return null;
    }

    protected boolean supportsInternal(LogObject optLogEvent) {
        return true;
    }

}
