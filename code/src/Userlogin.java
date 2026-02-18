// import java.awt.*;
// import java.awt.event.*;
// import java.sql.*;
// import javax.swing.*;

// public class Userlogin extends JFrame {

//     private JPanel contentPane;
//     private JTextField textUsername;
//     private JPasswordField passwordField;

//     public Userlogin() {
//         setTitle("🎫 Stadium Management Login");
//         setBounds(450, 200, 400, 300);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setResizable(false);

//         contentPane = new JPanel();
//         contentPane.setLayout(null);
//         setContentPane(contentPane);

//         JLabel lblTitle = new JLabel("Login");
//         lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
//         lblTitle.setBounds(140, 20, 120, 40);
//         contentPane.add(lblTitle);

//         JLabel lblUser = new JLabel("Username:");
//         lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//         lblUser.setBounds(50, 80, 100, 25);
//         contentPane.add(lblUser);

//         textUsername = new JTextField();
//         textUsername.setBounds(150, 80, 180, 25);
//         contentPane.add(textUsername);

//         JLabel lblPass = new JLabel("Password:");
//         lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//         lblPass.setBounds(50, 120, 100, 25);
//         contentPane.add(lblPass);

//         passwordField = new JPasswordField();
//         passwordField.setBounds(150, 120, 180, 25);
//         contentPane.add(passwordField);

//         JButton btnLogin = new JButton("Login");
//         btnLogin.setBounds(130, 180, 120, 35);
//         contentPane.add(btnLogin);

//         btnLogin.addActionListener(e -> {
//             String username = textUsername.getText();
//             String password = new String(passwordField.getPassword());

//             if (username.isEmpty() || password.isEmpty()) {
//                 JOptionPane.showMessageDialog(this, "❌ Please fill all fields.");
//                 return;
//             }

//             try {
//                 Connection con = DBConnection.getConnection();
//                 String query = "SELECT role FROM users WHERE username = ? AND password = ?";
//                 PreparedStatement pst = con.prepareStatement(query);
//                 pst.setString(1, username);
//                 pst.setString(2, password);

//                 ResultSet rs = pst.executeQuery();
//                 if (rs.next()) {
//                     String role = rs.getString("role");
//                     JOptionPane.showMessageDialog(this, "✅ Login successful! Role: " + role);
//                     dispose();

//                     if (role.equalsIgnoreCase("admin")) {
//                         new Admin_host().setVisible(true);
//                     } else {
//                         new User_host().setVisible(true); // We’ll make this screen next
//                     }
//                 } else {
//                     JOptionPane.showMessageDialog(this, "❌ Invalid credentials.");
//                 }
//                 con.close();
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//                 JOptionPane.showMessageDialog(this, "⚠️ Database error: " + ex.getMessage());
//             }
//         });
//     }
// }
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Userlogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Userlogin() {
        setTitle("👤 Login - Stadium Management");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(130, 20, 120, 30);
        add(lblTitle);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 70, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 70, 180, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 110, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 180, 25);
        add(passwordField);

        JButton loginBtn = new JButton("🔓 Login");
        loginBtn.setBounds(130, 160, 120, 35);
        add(loginBtn);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Userlogin.this, "❗ Please fill all fields.");
                    return;
                }

                try (Connection conn = DBConnection.getConnection()) {
                    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String role = rs.getString("role");  // Make sure your 'users' table has this column
                        JOptionPane.showMessageDialog(Userlogin.this, "✅ Login successful! Role: " + role);
                        dispose(); // Close login window

                        // Load the correct dashboard
                        if ("admin".equalsIgnoreCase(role)) {
                            new Admin_host().setVisible(true);   // Admin screen
                        } else {
                            new User_host().setVisible(true);    // Booking screen
                        }

                    } else {
                        JOptionPane.showMessageDialog(Userlogin.this, "❌ Invalid credentials!");
                    }

                    rs.close();
                    ps.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Userlogin.this, "⚠️ Database error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Userlogin();
    }
}
