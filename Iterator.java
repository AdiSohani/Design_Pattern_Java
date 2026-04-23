// =========================================================
// ITERATOR PATTERN — Reading a Book
// The Book exposes an Iterator (bookmark) to traverse pages
// sequentially without revealing internal structure.
// =========================================================
import java.util.*;

public class Iterator {

    // Iterator interface
    interface BookIterator<T> {
        boolean hasNext();
        T       next();
        void    reset();
    }

    // Aggregate interface
    interface Iterable<T> {
        BookIterator<T> iterator();
    }

    // Element
    static class Page {
        final int    number;
        final String content;
        Page(int number, String content) { this.number = number; this.content = content; }
        public String toString() { return "Page " + number + ": " + content; }
    }

    // Concrete aggregate
    static class Book implements Iterable<Page> {
        private final String  title;
        private final Page[]  pages;

        Book(String title, Page... pages) { this.title = title; this.pages = pages; }

        public BookIterator<Page> iterator() {
            return new BookIterator<Page>() {
                int index = 0;
                public boolean hasNext() { return index < pages.length; }
                public Page    next()    { return pages[index++]; }
                public void    reset()   { index = 0; }
            };
        }

        // Reverse iterator — reads book from last page to first
        BookIterator<Page> reverseIterator() {
            return new BookIterator<Page>() {
                int index = pages.length - 1;
                public boolean hasNext() { return index >= 0; }
                public Page    next()    { return pages[index--]; }
                public void    reset()   { index = pages.length - 1; }
            };
        }

        String getTitle() { return title; }
    }

    public static void main(String[] args) {
        System.out.println("=== Iterator Pattern ===\n");

        Book book = new Book("The Java Chronicles",
                new Page(1, "Once upon a time, there was a programmer..."),
                new Page(2, "She discovered design patterns one morning."),
                new Page(3, "A dragon (legacy code) blocked her path."),
                new Page(4, "She wielded the Decorator and Proxy patterns."),
                new Page(5, "The dragon was refactored. Peace returned.")
        );

        System.out.println("Reading \"" + book.getTitle() + "\" forward:\n");
        BookIterator<Page> reader = book.iterator();
        while (reader.hasNext()) {
            System.out.println("  " + reader.next());
        }

        System.out.println("\nFlipping back to re-read (reverse iterator):\n");
        BookIterator<Page> reverse = book.reverseIterator();
        while (reverse.hasNext()) {
            System.out.println("  " + reverse.next());
        }
    }
}