package com.ibingbo.test.thread;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;

/**
 * Created by zhangbingbing on 2016/12/9.
 */
public class InetTest {
    @Test
    public void testSome() throws UnknownHostException {

        System.out.println(InetAddress.getByName("localhost"));
        System.out.println(InetAddress.getByName("www.baidu.com"));
    }

    @Test
    public void testUrlEncode() throws UnsupportedEncodingException {
        String str = URLEncoder.encode("你好", "UTF-8");
        System.out.print(str);
        System.out.print(URLDecoder.decode(str,"UTF-8"));
    }
}
