package main;

import java.time.LocalDate;

public class Book implements Comparable<Book> {

    private final String author;
    private final String title;
    private final LocalDate publishedOn;

    public Book(String author, String title, LocalDate publishedOn) {
        this.author = author;
        this.title = title;
        this.publishedOn = publishedOn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishedOn=" + publishedOn +
                '}';
    }

    @Override
    public int compareTo(Book that){
        return this.title.compareTo(that.title);
    }
}
