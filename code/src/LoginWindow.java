// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.sql.*;

// public class LoginWindow extends JFrame {
//     private JTextField usernameField;
//     private JPasswordField passwordField;

//     public LoginWindow() {
//         setTitle("👤 Login - Stadium Management");
//         setSize(400, 250);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(null);

//         JLabel lblTitle = new JLabel("Login", SwingConstants.CENTER);
//         lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
//         lblTitle.setBounds(130, 20, 120, 30);
//         add(lblTitle);

//         JLabel userLabel = new JLabel("Username:");
//         userLabel.setBounds(50, 70, 100, 25);
//         add(userLabel);

//         usernameField = new JTextField();
//         usernameField.setBounds(150, 70, 180, 25);
//         add(usernameField);

//         JLabel passLabel = new JLabel("Password:");
//         passLabel.setBounds(50, 110, 100, 25);
//         add(passLabel);

//         passwordField = new JPasswordField();
//         passwordField.setBounds(150, 110, 180, 25);
//         add(passwordField);

//         JButton loginBtn = new JButton("🔓 Login");
//         loginBtn.setBounds(130, 160, 120, 35);
//         add(loginBtn);

//         loginBtn.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 String username = usernameField.getText().trim();
//                 String password = new String(passwordField.getPassword());

//                 if (username.isEmpty() || password.isEmpty()) {
//                     JOptionPane.showMessageDialog(LoginWindow.this, "❗ Please fill all fields.");
//                     return;
//                 }

//                 try (Connection conn = DBConnection.getConnection()) {
//                     String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
//                     PreparedStatement ps = conn.prepareStatement(sql);
//                     ps.setString(1, username);
//                     ps.setString(2, password);
//                     ResultSet rs = ps.executeQuery();

//                     if (rs.next()) {
//                         JOptionPane.showMessageDialog(LoginWindow.this, "✅ Login successful!");
//                         dispose();
//                         new Userlogin().setVisible(true);  // Redirect to Userlogin (like before)
//                     } else {
//                         JOptionPane.showMessageDialog(LoginWindow.this, "❌ Invalid credentials!");
//                     }

//                 } catch (Exception ex) {
//                     ex.printStackTrace();
//                     JOptionPane.showMessageDialog(LoginWindow.this, "⚠️ Database error: " + ex.getMessage());
//                 }
//             }
//         });

//         setVisible(true);
//     }
// }

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.sql.*;

// public class LoginWindow extends JFrame {
//     private JTextField usernameField;
//     private JPasswordField passwordField;

//     public LoginWindow() {
//         setTitle("👤 Login - Stadium Management");
//         setSize(400, 250);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(null);

//         JLabel lblTitle = new JLabel("Login", SwingConstants.CENTER);
//         lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
//         lblTitle.setBounds(130, 20, 120, 30);
//         add(lblTitle);

//         JLabel userLabel = new JLabel("Username:");
//         userLabel.setBounds(50, 70, 100, 25);
//         add(userLabel);

//         usernameField = new JTextField();
//         usernameField.setBounds(150, 70, 180, 25);
//         add(usernameField);

//         JLabel passLabel = new JLabel("Password:");
//         passLabel.setBounds(50, 110, 100, 25);
//         add(passLabel);

//         passwordField = new JPasswordField();
//         passwordField.setBounds(150, 110, 180, 25);
//         add(passwordField);

//         JButton loginBtn = new JButton("🔓 Login");
//         loginBtn.setBounds(130, 160, 120, 35);
//         add(loginBtn);

//         loginBtn.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 String username = usernameField.getText().trim();
//                 String password = new String(passwordField.getPassword());

//                 if (username.isEmpty() || password.isEmpty()) {
//                     JOptionPane.showMessageDialog(LoginWindow.this, "❗ Please fill all fields.");
//                     return;
//                 }

//                 try (Connection conn = DBConnection.getConnection()) {
//                     String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
//                     PreparedStatement ps = conn.prepareStatement(sql);
//                     ps.setString(1, username);
//                     ps.setString(2, password);
//                     ResultSet rs = ps.executeQuery();

//                     if (rs.next()) {
//                         JOptionPane.showMessageDialog(LoginWindow.this, "✅ Login successful!");
//                         dispose(); // Close login window

//                         String role = rs.getString("role"); // Ensure your DB has this column

//                         if ("admin".equalsIgnoreCase(role)) {
//                             new Admin_host().setVisible(true);
//                         } else {
//                             new User_host().setVisible(true);
//                         }

//                     } else {
//                         JOptionPane.showMessageDialog(LoginWindow.this, "❌ Invalid credentials!");
//                     }

//                 } catch (Exception ex) {
//                     ex.printStackTrace();
//                     JOptionPane.showMessageDialog(LoginWindow.this, "⚠️ Database error: " + ex.getMessage());
//                 }
//             }
//         });

//         setVisible(true);
//     }
// }
