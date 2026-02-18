import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class User_host extends JFrame {

    private JPanel contentPane;
    private JTextField textName, textAge, textPromo;
    private JComboBox<String> comboEvent;
    private JRadioButton vipRadio, generalRadio;
    private JButton bookButton;

    public User_host() {
        setTitle("🎫 Book Stadium Tickets");
        setBounds(400, 200, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 44, 52)); // Dark background
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblHeader = new JLabel("Book Your Ticket", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setBounds(250, 20, 400, 40);
        contentPane.add(lblHeader);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblName.setForeground(Color.WHITE);
        lblName.setBounds(100, 90, 100, 25);
        contentPane.add(lblName);

        textName = new JTextField();
        textName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textName.setBounds(200, 90, 200, 30);
        contentPane.add(textName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblAge.setForeground(Color.WHITE);
        lblAge.setBounds(450, 90, 100, 25);
        contentPane.add(lblAge);

        textAge = new JTextField();
        textAge.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textAge.setBounds(520, 90, 100, 30);
        contentPane.add(textAge);

        JLabel lblEvent = new JLabel("Event");
        lblEvent.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblEvent.setForeground(Color.WHITE);
        lblEvent.setBounds(100, 150, 100, 25);
        contentPane.add(lblEvent);

        comboEvent = new JComboBox<>();
        comboEvent.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comboEvent.setBounds(200, 150, 420, 30);
        contentPane.add(comboEvent);
        loadEvents();

        JLabel lblSeat = new JLabel("Seat Type");
        lblSeat.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSeat.setForeground(Color.WHITE);
        lblSeat.setBounds(100, 210, 100, 25);
        contentPane.add(lblSeat);

        vipRadio = new JRadioButton("VIP");
        vipRadio.setBounds(200, 210, 60, 25);
        vipRadio.setBackground(new Color(40, 44, 52));
        vipRadio.setForeground(Color.WHITE);
        generalRadio = new JRadioButton("General");
        generalRadio.setBounds(270, 210, 100, 25);
        generalRadio.setBackground(new Color(40, 44, 52));
        generalRadio.setForeground(Color.WHITE);

        ButtonGroup seatGroup = new ButtonGroup();
        seatGroup.add(vipRadio);
        seatGroup.add(generalRadio);
        contentPane.add(vipRadio);
        contentPane.add(generalRadio);

        JCheckBox promoCheck = new JCheckBox("Use Promo Code");
        promoCheck.setBounds(100, 260, 160, 25);
        promoCheck.setBackground(new Color(40, 44, 52));
        promoCheck.setForeground(Color.WHITE);
        contentPane.add(promoCheck);

        textPromo = new JTextField();
        textPromo.setEnabled(false);
        textPromo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textPromo.setBounds(270, 260, 180, 30);
        contentPane.add(textPromo);

        promoCheck.addActionListener(e -> textPromo.setEnabled(promoCheck.isSelected()));

        bookButton = new JButton("🎟️ Book Ticket");
        bookButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        bookButton.setBounds(350, 320, 200, 50);
		bookButton.setBackground(new Color(175, 238, 238));
        bookButton.setForeground(Color.WHITE);
        contentPane.add(bookButton);

        bookButton.addActionListener(e -> showTicketPreview());
    }

    private void loadEvents() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT event_name, match_date FROM events");
            while (rs.next()) {
                String name = rs.getString("event_name");
                String date = rs.getString("match_date");
                comboEvent.addItem(name + " - " + date);
            }
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Failed to load events: " + ex.getMessage());
        }
    }

    private void showTicketPreview() {
        String name = textName.getText();
        String ageStr = textAge.getText();
        String selectedEvent = (String) comboEvent.getSelectedItem();
        String seatType = vipRadio.isSelected() ? "VIP" : generalRadio.isSelected() ? "General" : null;
        String promo = textPromo.isEnabled() ? textPromo.getText() : "None";

        if (name.isEmpty() || ageStr.isEmpty() || seatType == null || selectedEvent == null) {
            JOptionPane.showMessageDialog(this, "❌ Please fill all fields.");
            return;
        }

        String preview = "\n🎫 Ticket Preview\n" +
                         "-------------------------\n" +
                         "Name: " + name + "\n" +
                         "Age: " + ageStr + "\n" +
                         "Event: " + selectedEvent + "\n" +
                         "Seat Type: " + seatType + "\n" +
                         "Promo Code: " + promo + "\n" +
                         "-------------------------\n\nConfirm booking?";

        int confirm = JOptionPane.showConfirmDialog(this, preview, "Confirm Ticket", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            bookTicket(name, ageStr, selectedEvent, seatType, promo);
        }
    }

    private void bookTicket(String name, String ageStr, String selectedEvent, String seatType, String promo) {
        try {
            int age = Integer.parseInt(ageStr);
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO bookings (user_name, user_age, event_detail, seat_type, promo_code) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, selectedEvent);
            pst.setString(4, seatType);
            pst.setString(5, promo);

            int res = pst.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(this, "✅ Ticket booked successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Failed to book ticket.");
            }
            con.close();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "❌ Age must be a number.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                User_host frame = new User_host();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}