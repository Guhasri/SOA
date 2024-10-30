import java.sql.*;
public class BankingJDBC {

    boolean isDeposit=false;

    private Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/banking";
            String user = "root";
            String password = "password";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void add(BankingFronted bf_object){
        try (Connection conn = connect()) {
            isDeposit=true;
            String sql = "INSERT INTO transactions (bf_object.amount bf_object.account_no) VALUES (?, ?, ?, ?)";

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(BankingFronted bf_object){
        try (Connection conn = connect()) {
            String sql = isDeposit? "UPDATE accounts SET balance = balance + bf_object.amount WHERE account_no = bf_object.account_no"
                    : "UPDATE accounts SET balance = balance - bf_object.amount WHERE account_no = bf_object.account_no";

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public double getBal(BankingFronted bf_object){
        try (Connection conn = connect()) {
            String sql = "SELECT balance FROM accounts WHERE account_no = bf_object.account_no";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,bf_object.account_no);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
