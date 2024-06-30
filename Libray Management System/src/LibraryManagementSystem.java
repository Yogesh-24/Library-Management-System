import java.util.Scanner;
import java.util.List;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Show Available Books");
            System.out.println("2. Add Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Add Member");
            System.out.println("5. Search Book by ISBN");
            System.out.println("6. Search Member by ID");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. Show Details");
            System.out.println("10. Log Out");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Option to show available books
                    System.out.println("--- Available Books ---");
                    List<Book> availableBooks = Book.getAvailableBooks();
                    if (availableBooks.isEmpty()) {
                        System.out.println("No books currently available.");
                    } else {
                        for (Book book : availableBooks) {
                            System.out.println(book);
                        }
                    }
                    break;

                case 2:
                    // Option to add a book
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Book ISBN(International Standard Book Number): ");
                    String isbn = scanner.nextLine();
                    Book.addBook(title, author, isbn);
                    break;

                case 3:
                    // Option to remove a book
                    System.out.print("Enter Book ID to remove: ");
                    int bookIdToRemove = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    Book.removeBook(bookIdToRemove);
                    break;

                case 4:
                    // Option to add a member
                    System.out.print("Enter Member Name: ");
                    String name = scanner.nextLine();
                    Member.addMember(name);
                    break;

                case 5:
                    // Option to search for a book by ISBN
                    System.out.print("Enter Book ISBN to search: ");
                    isbn = scanner.nextLine();
                    Book searchedBook = Book.searchBookByIsbn(isbn);
                    if (searchedBook != null) {
                        System.out.println(searchedBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 6:
                    // Option to search for a member by ID
                    System.out.print("Enter Member ID to search: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    Member searchedMember = Member.searchMemberById(memberId);
                    if (searchedMember != null) {
                        System.out.println(searchedMember);
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;

                case 7:
                    // Option to borrow a book
                    System.out.print("Enter Member ID: ");
                    memberId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter Book ISBN to borrow: ");
                    isbn = scanner.nextLine();
                    Member borrowingMember = Member.searchMemberById(memberId);
                    Book bookToBorrow = Book.searchBookByIsbn(isbn);
                    if (borrowingMember != null && bookToBorrow != null) {
                        borrowingMember.borrowBook(bookToBorrow);
                    } else {
                        System.out.println("Invalid Member ID or Book ISBN.");
                    }
                    break;

                case 8:
                    // Option to return a book
                    System.out.print("Enter Member ID: ");
                    memberId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter Book ISBN to return: ");
                    isbn = scanner.nextLine();
                    Member returningMember = Member.searchMemberById(memberId);
                    Book bookToReturn = Book.searchBookByIsbn(isbn);
                    if (returningMember != null && bookToReturn != null) {
                        returningMember.returnBook(bookToReturn);
                    } else {
                        System.out.println("Invalid Member ID or Book ISBN.");
                    }
                    break;

                case 9:
                    // Option to show details (book or member)
                    showDetailsMenu(scanner);
                    break;

                case 10:
                    System.out.println("Goodbye Keep Reading!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showDetailsMenu(Scanner scanner) {
        System.out.println("\n--- Show Details ---");
        System.out.println("1. Show Book Details");
        System.out.println("2. Show Member Details");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter Book ISBN to show details: ");
                String isbn = scanner.nextLine();
                Book searchedBook = Book.searchBookByIsbn(isbn);
                if (searchedBook != null) {
                    System.out.println(searchedBook);
                } else {
                    System.out.println("Book not found.");
                }
                break;

            case 2:
                System.out.print("Enter Member ID to show details: ");
                int memberId = scanner.nextInt();
                scanner.nextLine(); // consume newline
                Member searchedMember = Member.searchMemberById(memberId);
                if (searchedMember != null) {
                    System.out.println(searchedMember);
                } else {
                    System.out.println("Member not found.");
                }
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
