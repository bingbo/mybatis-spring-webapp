package com.ibingbo.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * SAX处理的优点非常类似于流媒体的优点。分析能够立即开始，而不是等待所有的数据被处理。而且，由于应用程序只是在读取数据时检查数据，因此不需要将数据存储在内存中。这对于大型文档来说是个巨大的优点。事实上，应用程序甚至不必解析整个文档；它可以在某个条件得到满足时停止解析。一般来说，SAX还比它的替代者DOM快许多。

 选择DOM还是选择SAX？ 对于需要自己编写代码来处理XML文档的开发人员来说， 选择DOM还是SAX解析模型是一个非常重要的设计决策。 DOM采用建立树形结构的方式访问XML文档，而SAX采用的是事件模型。

 DOM解析器把XML文档转化为一个包含其内容的树，并可以对树进行遍历。用DOM解析模型的优点是编程容易，开发人员只需要调用建树的指令，然后利用navigation APIs访问所需的树节点来完成任务。可以很容易的添加和修改树中的元素。然而由于使用DOM解析器的时候需要处理整个XML文档，所以对性能和内存的要求比较高，尤其是遇到很大的XML文件的时候。由于它的遍历能力，DOM解析器常用于XML文档需要频繁的改变的服务中。

 SAX解析器采用了基于事件的模型，它在解析XML文档的时候可以触发一系列的事件，当发现给定的tag的时候，它可以激活一个回调方法，告诉该方法制定的标签已经找到。SAX对内存的要求通常会比较低，因为它让开发人员自己来决定所要处理的tag.特别是当开发人员只需要处理文档中所包含的部分数据时，SAX这种扩展能力得到了更好的体现。但用SAX解析器的时候编码工作会比较困难，而且很难同时访问同一个文档中的多处不同数据。

 【优势】
 ①不需要等待所有数据都被处理，分析就能立即开始。
 ②只在读取数据时检查数据，不需要保存在内存中。
 ③可以在某个条件得到满足时停止解析，不必解析整个文档。
 ④效率和性能较高，能解析大于系统内存的文档。

 【缺点】
 ①需要应用程序自己负责TAG的处理逻辑（例如维护父/子关系等），文档越复杂程序就越复杂。
 ②单向导航，无法定位文档层次，很难同时访问同一文档的不同部分数据，不支持XPath。


 如果XML文档较大且不考虑移植性问题建议采用DOM4J；如果XML文档较小则建议采用JDOM；如果需要及时处理而不需要保存数据则考虑SAX。
 * Created by bing on 2017/1/4.
 */
public class SAXXML {

    public static void main(String[] args) throws Exception {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(new BookHandler());
        xmlReader.parse("src/main/java/com/ibingbo/xml/books.xml");
    }

    private static class BookHandler extends DefaultHandler{

        boolean hasAttribute = false;
        Attributes attributes = null;
        @Override
        public void startDocument() throws SAXException {
            System.out.println("start document ...");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("end document ...");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.println(qName);
            if (attributes.getLength() > 0) {
                for (int i=0;i<attributes.getLength();i++) {
                    System.out.println(attributes.getQName(i) + " : " + attributes.getValue(i));
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            System.out.println(new String(ch, start, length));
        }
    }

}
