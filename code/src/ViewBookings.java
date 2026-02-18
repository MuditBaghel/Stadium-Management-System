// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.sql.*;

// public class ViewBookings extends JFrame {
//     JTable table;
//     DefaultTableModel model;

//     public ViewBookings(String role, String fullName) {
//         setTitle("📋 View Bookings");
//         setSize(700, 400);
//         setLocationRelativeTo(null);
//         setLayout(new BorderLayout());

//         // Table Model
//         model = new DefaultTableModel();
//         table = new JTable(model);
//         JScrollPane scrollPane = new JScrollPane(table);
//         add(scrollPane, BorderLayout.CENTER);

//         model.addColumn("Booking ID");
//         model.addColumn("User");
//         model.addColumn("Event");
//         model.addColumn("Seat Type");
//         model.addColumn("Promo Code");

//         loadBookings(role, fullName);

//         // Optional: Add Back Button
//         JButton backBtn = new JButton("⬅ Back");
//         add(backBtn, BorderLayout.SOUTH);
//         backBtn.addActionListener(e -> {
//             dispose();
//             new Dashboard(fullName, role); // Return to dashboard
//         });

//         setVisible(true);
//     }

//     private void loadBookings(String role, String fullName) {
//         try (Connection conn = DBConnection.getConnection()) {
//             String sql;

//             if (role.equalsIgnoreCase("admin")) {
//                 // Admin sees all bookings
//                 sql = "SELECT b.id, u.full_name, e.event_name, b.seat_type, b.promo_code " +
//                       "FROM bookings b " +
//                       "JOIN users u ON b.user_id = u.id " +
//                       "JOIN events e ON b.event_id = e.id";
//             } else {
//                 // User sees their own bookings
//                 sql = "SELECT b.id, u.full_name, e.event_name, b.seat_type, b.promo_code " +
//                       "FROM bookings b " +
//                       "JOIN users u ON b.user_id = u.id " +
//                       "JOIN events e ON b.event_id = e.id " +
//                       "WHERE u.full_name = ?";
//             }

//             PreparedStatement ps = conn.prepareStatement(sql);

//             if (!role.equalsIgnoreCase("admin")) {
//                 ps.setString(1, fullName);
//             }

//             ResultSet rs = ps.executeQuery();

//             boolean hasResults = false;

//             while (rs.next()) {
//                 hasResults = true;
//                 model.addRow(new Object[]{
//                     rs.getInt("id"),
//                     rs.getString("full_name"),
//                     rs.getString("event_name"),
//                     rs.getString("seat_type"),
//                     rs.getString("promo_code")
//                 });
//             }

//             if (!hasResults) {
//                 JOptionPane.showMessageDialog(this, "📭 No bookings found.");
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(this, "❌ Failed to load bookings.");
//         }
//     }
// }
