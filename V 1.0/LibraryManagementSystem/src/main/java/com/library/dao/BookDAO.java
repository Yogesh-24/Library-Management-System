package com.library.dao;

import com.library.model.Book;
import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    Book searchBookByIsbn(String isbn);
    List<Book> getAvailableBooks();
    void updateBookAvailability(int bookId, boolean isAvailable);
    void removeBook(int bookId);
}
