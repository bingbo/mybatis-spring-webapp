package com.ibingbo.xml;

import org.apache.commons.digester3.Digester;

import java.io.File;

/**
 * Digester 就是一种用来把一个 XML 转化为一个与该 XML 结构类似的 JavaBean。你可以把 XML 根元素想象成一个 JavaBean， 该根元素的 attribute 就是这个 JavaBean 的各种 Field，当该根元素有其他子 tag 时，又要把这个子 tag 想象成一个个新的 XML，将其视为一个新的 JavaBean， 并作为一个 Field 加入到父 Bean 当中，然后以此类推，通过循环的方式将整个 XML 进行解析
 * Created by bing on 2017/1/4.
 */
public class DigesterXML {

    public static void main(String[] args) throws Exception {
        File file = new File("src/main/java/com/ibingbo/xml/books.xml");
        Digester digester = new Digester();
        //创建Books对象
        digester.addObjectCreate("books", Books.class);
        //添加Books对象的一些属性
        digester.addSetProperties("books");

        //创建Book对象
        digester.addObjectCreate("books/book", Book.class);
        //添加Book对象的一些属性
        digester.addSetProperties("books/book");

        //设置Book对象的指定属性的值
        digester.addBeanPropertySetter("books/book/title", "title");
        digester.addBeanPropertySetter("books/book/author", "author");

        //下一个Book对象，解析并添加到Books中，通过addBook方法
        digester.addSetNext("books/book", "addBook", "com.xml.Book");

        Books books = (Books) digester.parse(file);

        System.out.println(books);
    }
}
