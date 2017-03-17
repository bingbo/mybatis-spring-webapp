package com.ibingbo.xml;

import com.sun.xml.internal.stream.events.XMLEventAllocatorImpl;

import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 * StAX把重点放在流上，StAX使应用程序能够把 XML 作为一个事件流来处理；其实SAX方式也是基于事件流的XML处理方法，但二者不同之处在于，SAX是基于观察者模式，我们需要提供事件处理程序并注册到解析器，解析器在指定事件发生时回调我们提供的程序；而StAX允许我们的程序把事件逐个”拉“出来，这样StAX就有更大的灵活性，对于我们不感兴趣的事件就没有必要将其”拉“出来处理。
 StAX提供了两套API用来处理XML，分别提供了不同程度的抽象。基于指针的 API 把 XML 作为一个标记（或事件）流来处理；应用程序可以检查解析器的状态，获得解析的上一个标记的信息，然后再处理下一个标记，依此类推。这是一种低层 API，尽管效率高，但是没有提供底层 XML 结构的抽象。基于迭代器的 API 把 XML 作为一系列事件对象来处理。应用程序只需要确定解析事件的类型，将其转换成对应的具体类型，然后利用其方法获得属于该事件的信息
 * Created by bing on 2017/1/4.
 */
public class STAXXML {

    public static void main(String[] args) throws Exception {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setEventAllocator(new XMLEventAllocatorImpl());
//        XMLStreamReader xmlStreamReader = XMLStreamReaderFactory.create("books.xml",new FileInputStream("src/main/java/com/ibingbo/xml/books.xml"),false);
        //基于流、游标的读取
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream("src/main/java/com/ibingbo/xml/books.xml"));
        //parse(xmlStreamReader);

        //基于事件的读取
        XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/java/com/ibingbo/xml/books.xml"));
        //可以封装过滤器
        eventReader = xmlInputFactory.createFilteredReader(eventReader, new EventFilter() {
            @Override
            public boolean accept(XMLEvent event) {
                return true;
            }
        });
        parse(eventReader);
    }

    private static void parse(XMLEventReader eventReader) throws Exception {
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartDocument()) {
                System.out.println("start document ...");
                System.out.println(event.getSchemaType());
                System.out.println(event.isNamespace());
            } else if (event.isStartElement()) {
                System.out.println(event.asStartElement().getName());
                Iterator<Attribute> iterator = event.asStartElement().getAttributes();
                if (null != iterator) {
                    while (iterator.hasNext()) {
                        Attribute attribute = iterator.next();
                        System.out.println(attribute.getName() + " : " + attribute.getValue());
                    }
                }
            } else if (event.isAttribute()) {
                System.out.println(event.asCharacters().getData());
            } else if (event.isCharacters()) {
                System.out.println(event.asCharacters().getData());
            }
        }
    }
    private static void parse(XMLStreamReader xmlStreamReader) throws Exception {
        int eventType = xmlStreamReader.getEventType();
        while (xmlStreamReader.hasNext()) {
            eventType = xmlStreamReader.next();
            switch (eventType) {
                case XMLStreamConstants.ATTRIBUTE:

                    break;
                case XMLStreamConstants.START_DOCUMENT:
                    System.out.print("start document ..");
                    System.out.println(xmlStreamReader.getEncoding());
                    System.out.println(xmlStreamReader.getVersion());
                    System.out.println(xmlStreamReader.isStandalone());
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    System.out.println(xmlStreamReader.getLocalName());
                    int count = xmlStreamReader.getAttributeCount();
                    if (count > 0) {
                        for (int i = 0; i < count; i++) {
                            System.out.println(xmlStreamReader.getAttributeLocalName(i) + " : " + xmlStreamReader.getAttributeValue(i));
                        }
                    }
                    break;
                case XMLStreamConstants.CDATA:
                case XMLStreamConstants.COMMENT:
                case XMLStreamConstants.CHARACTERS:
                case XMLStreamConstants.SPACE:
                    System.out.println(xmlStreamReader.getText());
                    break;
                default:
                    break;
            }

        }
    }


}
