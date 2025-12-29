import java.sql.*;

public class ProductDAO {

    // Create table
    public static void createTable() throws Exception {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "name TEXT," +
                     "price REAL," +
                     "quantity INTEGER)";

        st.execute(sql);
        con.close();
    }

    // Insert product
    public static void addProduct(String name, double price, int qty) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("INSERT INTO products(name,price,quantity) VALUES(?,?,?)");

        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setInt(3, qty);

        ps.executeUpdate();
        con.close();
        System.out.println("✅ Product Added");
    }

    // View products
    public static void viewProducts() throws Exception {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM products");

        System.out.println("\nID | NAME | PRICE | QTY");
        System.out.println("------------------------");
        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("name") + " | " +
                rs.getDouble("price") + " | " +
                rs.getInt("quantity")
            );
        }
        con.close();
    }

    // Buy product
    public static void buyProduct(int id, int qty) throws Exception {
        Connection con = DBUtil.getConnection();

        PreparedStatement check =
            con.prepareStatement("SELECT quantity FROM products WHERE id=?");
        check.setInt(1, id);

        ResultSet rs = check.executeQuery();

        if (rs.next()) {
            int available = rs.getInt("quantity");
            if (available >= qty) {
                PreparedStatement update =
                    con.prepareStatement(
                        "UPDATE products SET quantity=quantity-? WHERE id=?");
                update.setInt(1, qty);
                update.setInt(2, id);
                update.executeUpdate();
                System.out.println("🛒 Purchase Successful");
            } else {
                System.out.println("❌ Not enough stock");
            }
        } else {
            System.out.println("❌ Product not found");
        }
        con.close();
    }
}
