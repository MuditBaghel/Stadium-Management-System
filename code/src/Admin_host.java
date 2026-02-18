import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.text.SimpleDateFormat;

public class Admin_host extends JFrame {

    private JPanel contentPane;
    private JTextField textEvent, textTime, textSeats;
    private JButton addButton, logoutButton;

    public Admin_host() {
        setTitle("🎪 Admin - Add New Event");
        setBounds(400, 200, 900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE); // Light theme
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblHeader = new JLabel("Admin - Add Event", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblHeader.setBounds(250, 20, 400, 40);
        contentPane.add(lblHeader);

        JLabel lblEvent = new JLabel("Event Name");
        lblEvent.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblEvent.setBounds(100, 90, 200, 25);
        contentPane.add(lblEvent);

        textEvent = new JTextField();
        textEvent.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textEvent.setBounds(300, 90, 300, 30);
        contentPane.add(textEvent);

        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTime.setBounds(100, 140, 200, 25);
        contentPane.add(lblTime);

        textTime = new JTextField();
        textTime.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textTime.setBounds(300, 140, 300, 30);
        contentPane.add(textTime);

        JLabel lblSeats = new JLabel("Total Seats");
        lblSeats.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSeats.setBounds(100, 190, 200, 25);
        contentPane.add(lblSeats);

        textSeats = new JTextField();
        textSeats.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textSeats.setBounds(300, 190, 300, 30);
        contentPane.add(textSeats);

        addButton = new JButton("➕ Add Event");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);
        addButton.setBounds(350, 250, 200, 50);
        contentPane.add(addButton);

        logoutButton = new JButton("🔙 Logout");
        logoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        logoutButton.setBounds(700, 20, 120, 30);
        contentPane.add(logoutButton);

        addButton.addActionListener(e -> addEvent());
        logoutButton.addActionListener(e -> {
            dispose();
            // Navigate to login (you can implement LoginWindow later)
        });
    }

    private void addEvent() {
        String event = textEvent.getText();
        String time = textTime.getText();
        String seats = textSeats.getText();

        if (event.isEmpty() || time.isEmpty() || seats.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Please fill all fields.");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();
            if (con == null) {
                throw new SQLException("Database connection is null");
            }
            String query = "INSERT INTO events (event_name, match_date) VALUES (?, CURDATE())";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, event);
            int res = pst.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(this, "✅ Event added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Failed to add event.");
            }
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                Admin_host frame = new Admin_host();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
