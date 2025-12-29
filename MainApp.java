import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Create tables
        UserDAO.createTable();
        ProductDAO.createTable();

        System.out.println("---- WELCOME TO ECOMMERCE ----");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choice: ");
        int ch = sc.nextInt();
        sc.nextLine();

        boolean loggedIn = false;

        if (ch == 1) {
            System.out.print("Username: ");
            String u = sc.nextLine();
            System.out.print("Password: ");
            String p = sc.nextLine();
            UserDAO.register(u, p);
        }

        if (ch == 2) {
            System.out.print("Username: ");
            String u = sc.nextLine();
            System.out.print("Password: ");
            String p = sc.nextLine();

            loggedIn = UserDAO.login(u, p);

            if (!loggedIn) {
                System.out.println("❌ Invalid Login");
                System.exit(0);
            }
            System.out.println("✅ Login Successful");
        }

        // Ecommerce Menu after login
        while (true) {
            System.out.println("\n--- ECOMMERCE MENU ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Buy Product");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    ProductDAO.addProduct(name, price, qty);
                    break;

                case 2:
                    ProductDAO.viewProducts();
                    break;

                case 3:
                    System.out.print("Product ID: ");
                    int id = sc.nextInt();
                    System.out.print("Quantity: ");
                    int q = sc.nextInt();
                    ProductDAO.buyProduct(id, q);
                    break;

                case 4:
                    System.out.println("👋 Thank you");
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }
}