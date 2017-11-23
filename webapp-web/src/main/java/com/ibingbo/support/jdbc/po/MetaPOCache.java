package com.ibingbo.support.jdbc.po;

import java.util.HashMap;
import java.util.Map;

/**
 * MetaPOCache
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class MetaPOCache {

    private static final Map<Class<? extends AbstractPO>, POClassInfo<? extends AbstractPO>> PO_CLASS_INFO_MAP = new
            HashMap<>();

    public static <T extends AbstractPO> POClassInfo<T> getPOClassInfo(Class<T> poClass) {
        POClassInfo<T> poClassInfo = (POClassInfo<T>) PO_CLASS_INFO_MAP.get(poClass);
        if (poClassInfo == null) {
            poClassInfo = new POClassInfo<T>(poClass);
            PO_CLASS_INFO_MAP.put(poClass, poClassInfo);
        }
        return poClassInfo;
    }
}
