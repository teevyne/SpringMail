package main;

import java.util.*;
import java.util.stream.Collectors;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books(){
        return Collections.unmodifiableList(books);
    }

    public void add(Book... bookToAdd){
        Arrays.stream(bookToAdd).forEach(books::add);
    }

    public List<Book> arrange(){
        return books.stream().sorted().collect(Collectors.toList());
    }

    public  List<Book> arrange(Comparator<Book> criteria){
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }
}
