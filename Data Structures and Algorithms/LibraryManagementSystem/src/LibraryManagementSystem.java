import java.util.Arrays;
import java.util.Comparator;

public class LibraryManagementSystem {

    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book binarySearch(Book[] books, String title) {

        int left = 0;
        int right = books.length - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            int result = books[mid].title.compareToIgnoreCase(title);

            if (result == 0)
                return books[mid];

            if (result < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return null;
    }

    public static void main(String[] args) {

        Book[] books = {
                new Book(101, "Java", "James Gosling"),
                new Book(102, "Python", "Guido van Rossum"),
                new Book(103, "C", "Dennis Ritchie"),
                new Book(104, "Data Structures", "Mark Allen")
        };

        System.out.println("Linear Search:");

        Book result1 = linearSearch(books, "Python");

        if (result1 != null)
            result1.displayBook();
        else
            System.out.println("Book not found");

        Arrays.sort(books, Comparator.comparing(book -> book.title));

        System.out.println("\nBinary Search:");

        Book result2 = binarySearch(books, "Java");

        if (result2 != null)
            result2.displayBook();
        else
            System.out.println("Book not found");
    }
}