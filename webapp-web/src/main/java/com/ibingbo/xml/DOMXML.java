package com.ibingbo.xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 DOM是用与平台和语言无关的方式表示XML文档的官方W3C标准。DOM是以层次结构组织的节点或信息片断的集合。这个层次结构允许开发人员在树中寻找特定信息。分析该结构通常需要加载整个文档和构造层次结构，然后才能做任何工作。由于它是基于信息层次的，因而DOM被认为是基于树或基于对象的。

 【优点】
 ①允许应用程序对数据和结构做出更改。
 ②访问是双向的，可以在任何时候在树中上下导航，获取和操作任意部分的数据。
 【缺点】
 ①通常需要加载整个XML文档来构造层次结构，消耗资源大。* Created by bing on 2017/1/4.
 */
public class DOMXML {

    public static void main(String[] args) throws Exception{
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/java/com/ibingbo/xml/books.xml"));

        //get root element
        Element rootElement = document.getDocumentElement();
//        NodeList children = rootElement.getChildNodes();
//        for (int i=0;i<children.getLength();i++) {
//            Node node = children.item(i);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                Element child = (Element) node;
//                System.out.println(child.getTagName());
//                System.out.println(" id: "+child.getAttribute("id"));
//                if (child.hasChildNodes()) {
//                    NodeList nodes=child.getChildNodes();
//                    for (int j=0;j<nodes.getLength();j++) {
//                        Node node1 = nodes.item(j);
//                        if (node1.getNodeType() == Node.ELEMENT_NODE) {
//                            Element element = (Element)node1;
//                            System.out.println(" "+element.getTagName() + ": "+element.getTextContent());
//                        }
//                    }
//                }
//            }
//
//        }
        parse(rootElement);

    }

    private static void parse(Element root) {
        System.out.println(root.getTagName());
        System.out.println(root.getTextContent());
        if (root.hasAttributes()) {
            NamedNodeMap map = root.getAttributes();
            for (int i=0;i<map.getLength();i++) {
                Node attribute = map.item(i);
                if (attribute.getNodeType() == Node.ATTRIBUTE_NODE) {
                    System.out.println(attribute.getNodeName()+" : "+attribute.getTextContent());
                }
            }
        }
        if (root.hasChildNodes()) {
            NodeList nodeList = root.getChildNodes();
            for (int j=0;j<nodeList.getLength();j++) {
                Node node = nodeList.item(j);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    parse((Element)node);
                }
            }
        }
    }
}
