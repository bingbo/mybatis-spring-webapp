package com.ibingbo.support.multi.datasource2;

/**
 * Created by bing on 17/7/22.
 */
public class DynamicDataSourceHolder {

    public static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void putDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSource() {
        return holder.get();
    }

    public static void clearHolder() {
        holder.remove();
    }
}
