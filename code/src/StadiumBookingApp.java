// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.sql.*;

// public class StadiumBookingApp extends JFrame {
//     JComboBox<String> userBox, eventBox;
//     JTextField seatTypeField, promoCodeField;
//     JTextArea displayArea;

//     public StadiumBookingApp() {
//         setTitle("Stadium Booking System");
//         setSize(500, 500);
//         setLayout(new BorderLayout());

//         JPanel topPanel = new JPanel(new GridLayout(5, 2));

//         userBox = new JComboBox<>();
//         eventBox = new JComboBox<>();
//         seatTypeField = new JTextField();
//         promoCodeField = new JTextField();

//         topPanel.add(new JLabel("Select User:"));
//         topPanel.add(userBox);

//         topPanel.add(new JLabel("Select Event:"));
//         topPanel.add(eventBox);

//         topPanel.add(new JLabel("Seat Type:"));
//         topPanel.add(seatTypeField);

//         topPanel.add(new JLabel("Promo Code:"));
//         topPanel.add(promoCodeField);

//         JButton bookBtn = new JButton("Book Now");
//         JButton fetchBtn = new JButton("Show Bookings");
//         topPanel.add(bookBtn);
//         topPanel.add(fetchBtn);

//         add(topPanel, BorderLayout.NORTH);

//         displayArea = new JTextArea();
//         add(new JScrollPane(displayArea), BorderLayout.CENTER);

//         // Populate dropdowns
//         loadUsers();
//         loadEvents();

//         // Booking Action
//         bookBtn.addActionListener(e -> bookSeat());
//         fetchBtn.addActionListener(e -> showAllBookings());

//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setVisible(true);
//     }

//     private void loadUsers() {
//         try (Connection conn = DBConnection.getConnection();
//              Statement stmt = conn.createStatement();
//              ResultSet rs = stmt.executeQuery("SELECT id, full_name FROM users")) {

//             while (rs.next()) {
//                 userBox.addItem(rs.getInt("id") + " - " + rs.getString("full_name"));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     private void loadEvents() {
//         try (Connection conn = DBConnection.getConnection();
//              Statement stmt = conn.createStatement();
//              ResultSet rs = stmt.executeQuery("SELECT id, event_name FROM events")) {

//             while (rs.next()) {
//                 eventBox.addItem(rs.getInt("id") + " - " + rs.getString("event_name"));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     private void bookSeat() {
//         try (Connection conn = DBConnection.getConnection()) {
//             String seatType = seatTypeField.getText();
//             String promo = promoCodeField.getText();

//             int userId = Integer.parseInt(userBox.getSelectedItem().toString().split(" - ")[0]);
//             int eventId = Integer.parseInt(eventBox.getSelectedItem().toString().split(" - ")[0]);

//             String sql = "INSERT INTO bookings (user_id, event_id, seat_type, promo_code) VALUES (?, ?, ?, ?)";
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ps.setInt(1, userId);
//             ps.setInt(2, eventId);
//             ps.setString(3, seatType);
//             ps.setString(4, promo.isEmpty() ? null : promo);
//             ps.executeUpdate();

//             JOptionPane.showMessageDialog(this, "Booking Successful!");
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     private void showAllBookings() {
//         displayArea.setText("");
//         try (Connection conn = DBConnection.getConnection();
//              Statement stmt = conn.createStatement();
//              ResultSet rs = stmt.executeQuery(
//                      "SELECT b.id, u.full_name, e.event_name, b.seat_type, b.promo_code " +
//                      "FROM bookings b " +
//                      "JOIN users u ON b.user_id = u.id " +
//                      "JOIN events e ON b.event_id = e.id")) {

//             while (rs.next()) {
//                 displayArea.append("Booking #" + rs.getInt("id") + "\n");
//                 displayArea.append("User: " + rs.getString("full_name") + "\n");
//                 displayArea.append("Event: " + rs.getString("event_name") + "\n");
//                 displayArea.append("Seat: " + rs.getString("seat_type") + "\n");
//                 displayArea.append("Promo: " + rs.getString("promo_code") + "\n\n");
//             }

//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void main(String[] args) {
//         new StadiumBookingApp();
//     }
// }
