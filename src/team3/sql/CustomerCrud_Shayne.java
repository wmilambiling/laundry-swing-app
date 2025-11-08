package team3.sql;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class CustomerCrud_Shayne {
    private final Connection conn;

    // ✅ Constructor - handles connection safely
    public CustomerCrud_Shayne() {
        Connection tempConn = null;
        try {
            tempConn = new DbConnection().createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn = tempConn;
    }

    // ✅ Get last inserted ID
    public int getLastInsertedId() {
        int id = -1;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()")) {
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    // ✅ Load customer data (all if limit == 0)
    public DefaultTableModel getCustomerData(int limit) {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "First Name", "Last Name", "House #", "Street", "Barangay", "City", "Phone"}, 0
        );

        try {
            String sql = "SELECT * FROM customer";
            if (limit > 0) {
                sql += " LIMIT ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            if (limit > 0) {
                ps.setInt(1, limit);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("house_num"),
                    rs.getString("street"),
                    rs.getString("barangay"),
                    rs.getString("city"),
                    rs.getString("phone")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    // ✅ Add new customer
    public void addCustomer(String first, String last, String house, String street, String brgy, String city, String phone) {
        String sql = "INSERT INTO customer (first_name, last_name, house_num, street, barangay, city, phone) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, first);
            ps.setString(2, last);
            ps.setString(3, house);
            ps.setString(4, street);
            ps.setString(5, brgy);
            ps.setString(6, city);
            ps.setString(7, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Update existing customer
    public void updateCustomer(int id, String first, String last, String house, String street, String brgy, String city, String phone) {
        String sql = "UPDATE customer SET first_name=?, last_name=?, house_num=?, street=?, barangay=?, city=?, phone=? WHERE customer_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, first);
            ps.setString(2, last);
            ps.setString(3, house);
            ps.setString(4, street);
            ps.setString(5, brgy);
            ps.setString(6, city);
            ps.setString(7, phone);
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete customer by ID
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE customer_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
