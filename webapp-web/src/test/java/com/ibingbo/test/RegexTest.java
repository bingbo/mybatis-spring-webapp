package com.ibingbo.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * RegexTest
 *
 * @author zhangbingbing
 * @date 18/1/9
 */
public class RegexTest {
    public static void main(String[] args) throws IOException {
        byte[] inByte = new byte[100];
        System.out.println(Pattern.matches("\\d*", "1234"));
        System.exit(0);

        List<String> list = new ArrayList<>();
        list.stream().filter(e -> e.toLowerCase() == "aaa").forEach(e -> System.out.println(e));
        list.stream().collect(Collectors.joining(","));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String aa = iterator.next();
            iterator.remove();
        }
        Set<com.ibingbo.models.User> set = new HashSet<>();
        while (true) {
            System.out.println("Enter your regex: ");
            System.in.read(inByte);
            Pattern pattern = Pattern.compile(new String(inByte));
            System.out.println("Enter input string to search: ");
            System.in.read(inByte);
            Matcher matcher = pattern.matcher(new String(inByte));
            boolean found = false;
            while (matcher.find()) {
                System.out.format("I found the text \"%s\" starting at index %d and ending at index %d.%n", matcher
                        .group(), matcher.start(), matcher.end());
                found = true;
            }
            if (!found) {
                System.out.format("No match found.%n");
            }
        }
    }
}
