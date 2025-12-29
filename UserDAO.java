import java.sql.*;

public class UserDAO {

    // Create users table
    public static void createTable() throws Exception {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "username TEXT UNIQUE," +
                     "password TEXT)";

        st.execute(sql);
        con.close();
    }

    // Register user
    public static void register(String user, String pass) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("INSERT INTO users(username,password) VALUES(?,?)");

        ps.setString(1, user);
        ps.setString(2, pass);
        ps.executeUpdate();

        con.close();
        System.out.println("✅ Registration Successful");
    }

    // Login user
    public static boolean login(String user, String pass) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");

        ps.setString(1, user);
        ps.setString(2, pass);

        ResultSet rs = ps.executeQuery();
        boolean status = rs.next();
        con.close();

        return status;
    }
}