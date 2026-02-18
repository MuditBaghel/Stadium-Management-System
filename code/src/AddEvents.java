import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class AddEvents extends JFrame {

    private JPanel contentPane;
    private JTextField textEventName;
    private JTextField textTime;
    private JTextField textSeats;

    public AddEvents() {
        setTitle("🎪 Admin - Add New Event");
        setBounds(450, 200, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Dark background panel
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(34, 34, 34)); // Dark gray
        setContentPane(contentPane);

        // Header
        JLabel header = new JLabel("Admin – Add Event");
        header.setFont(new Font("Segoe UI", Font.BOLD, 32));
        header.setForeground(Color.WHITE);
        header.setBounds(150, 30, 400, 40);
        contentPane.add(header);

        // Label - Event Name
        JLabel lblEventName = new JLabel("Event Name");
        lblEventName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblEventName.setForeground(Color.WHITE);
        lblEventName.setBounds(100, 100, 120, 30);
        contentPane.add(lblEventName);

        textEventName = new JTextField();
        styleTextField(textEventName, 240, 100);
        contentPane.add(textEventName);

        // Label - Time
        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTime.setForeground(Color.WHITE);
        lblTime.setBounds(100, 160, 120, 30);
        contentPane.add(lblTime);

        textTime = new JTextField();
        styleTextField(textTime, 240, 160);
        contentPane.add(textTime);

        // Label - Total Seats
        JLabel lblSeats = new JLabel("Total Seats");
        lblSeats.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSeats.setForeground(Color.WHITE);
        lblSeats.setBounds(100, 220, 120, 30);
        contentPane.add(lblSeats);

        textSeats = new JTextField();
        styleTextField(textSeats, 240, 220);
        contentPane.add(textSeats);

        // Stylish Add Button
        JButton addButton = new JButton("➕ Add Event");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        addButton.setBounds(240, 280, 160, 45);
        addButton.setBackground(new Color(0, 123, 255)); // Bootstrap Blue
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(new LineBorder(Color.WHITE));
        contentPane.add(addButton);

        addButton.addActionListener(e -> {
            String eventName = textEventName.getText();
            String time = textTime.getText();
            String seatsStr = textSeats.getText();

            if (eventName.isEmpty() || time.isEmpty() || seatsStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "❌ Please fill all required fields");
                return;
            }

            try {
                int totalSeats = Integer.parseInt(seatsStr);
                // TODO: Add database logic here
                JOptionPane.showMessageDialog(this, "✅ Event added successfully!");
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "❌ Seats must be a number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "⚠️ Error: " + ex.getMessage());
            }
        });

        // Logout Button
        JButton logoutButton = new JButton("⏴ Logout");
        logoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        logoutButton.setBounds(490, 20, 90, 30);
        logoutButton.setBackground(new Color(220, 53, 69)); // Bootstrap Danger
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorder(new LineBorder(Color.WHITE));
        contentPane.add(logoutButton);
    }

    private void styleTextField(JTextField field, int x, int y) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        field.setBounds(x, y, 250, 30);
        field.setBackground(new Color(50, 50, 50)); // Darker field
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(new LineBorder(Color.GRAY));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddEvents frame = new AddEvents();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
