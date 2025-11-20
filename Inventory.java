import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Inventory {

    private LinkedList<Book> books = new LinkedList<>();
    private Queue<String> orderQueue = new LinkedList<>();

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bookstore Inventory Management System!\n");

        int choice = 0;
        while (choice != 7) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Sort books by title");
            System.out.println("4. Search for a book by title");
            System.out.println("5. Add a customer order to the queue");
            System.out.println("6. Process the next customer order");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inventory.addBook(scanner);
                    break;
                case 2:
                    inventory.displayAllBooks();
                    break;
                case 3:
                    inventory.sortBooksByTitle();
                    break;
                case 4:
                    inventory.searchBookByTitle(scanner);
                    break;
                case 5:
                    inventory.addOrderToQueue(scanner);
                    break;
                case 6:
                    inventory.processNextOrder();
                    break;
                case 7:
                    System.out.println("Thank you for using the Bookstore Inventory Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println();
        }

        scanner.close();
    }

    public void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter book price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 

        books.add(new Book(title, author, isbn, price));
        System.out.println("Book added successfully!");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("The inventory is empty.");
            return;
        }

        System.out.println("--- All Books in Inventory ---");
        for (Book b : books) {
            System.out.println(b);
        }
        System.out.println("-----------------------------");
    }

    public void sortBooksByTitle() {
        if (books.size() < 2) {
            System.out.println("Not enough books to sort.");
            return;
        }

        System.out.println("Sorting books by title...");

        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - 1 - i; j++) {
                if (books.get(j).getTitle().compareToIgnoreCase(books.get(j + 1).getTitle()) > 0) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }

        System.out.println("Books sorted successfully!");
    }

    public void searchBookByTitle(Scanner scanner) {
        System.out.print("Enter the title of the book to search for: ");
        String title = scanner.nextLine().trim().toLowerCase();

        for (Book b : books) {
            if (b.getTitle().toLowerCase().equals(title)) {
                System.out.println("Book found:");
                System.out.println(b);
                return;
            }
        }

        System.out.println("Book not found.");
    }

    public void addOrderToQueue(Scanner scanner) {
        System.out.print("Enter the title of the book to order: ");
        String title = scanner.nextLine();

        orderQueue.add(title);
        System.out.println("Order for \"" + title + "\" has been added to the queue.");
    }

    public void processNextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in the queue.");
            return;
        }

        System.out.println("Processing next order...");
        String nextOrder = orderQueue.poll();
        System.out.println("Processed order for: " + nextOrder);
    }
}