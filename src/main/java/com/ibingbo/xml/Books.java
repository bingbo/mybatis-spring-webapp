package com.ibingbo.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing on 2017/1/4.
 */
public class Books {

    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return "Books{" +
                "books=" + books +
                '}';
    }
}
