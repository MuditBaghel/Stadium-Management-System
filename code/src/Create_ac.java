// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import java.sql.*;

// public class Create_ac extends JFrame {

//     private JPanel contentPane;
//     private JTextField firstname, lastname, email, username, mob;
//     private JPasswordField passwordField;

//     public static void main(String[] args) {
//         EventQueue.invokeLater(() -> {
//             try {
//                 Create_ac frame = new Create_ac();
//                 frame.setVisible(true);
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });
//     }

//     public Create_ac() {
//         setTitle("User Registration");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setBounds(450, 190, 1014, 597);
//         setResizable(false);

//         contentPane = new JPanel();
//         contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//         setContentPane(contentPane);
//         contentPane.setLayout(null);

//         JLabel lblTitle = new JLabel("New User Register");
//         lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 32));
//         lblTitle.setBounds(362, 52, 325, 50);
//         contentPane.add(lblTitle);

//         addLabelAndField("First name", 58, 152, firstname = new JTextField());
//         addLabelAndField("Last name", 58, 243, lastname = new JTextField());
//         addLabelAndField("Email address", 58, 324, email = new JTextField());
//         addLabelAndField("Username", 542, 152, username = new JTextField());
//         addLabelAndField("Password", 542, 243, passwordField = new JPasswordField());
//         addLabelAndField("Mobile number", 542, 324, mob = new JTextField());

//         JButton registerBtn = new JButton("Register");
//         registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 22));
//         registerBtn.setBounds(345, 420, 139, 50);
//         contentPane.add(registerBtn);

//         registerBtn.addActionListener(e -> registerUser());
//     }

//     private void addLabelAndField(String labelText, int x, int y, JComponent field) {
//         JLabel label = new JLabel(labelText);
//         label.setFont(new Font("Tahoma", Font.PLAIN, 18));
//         label.setBounds(x, y, 150, 30);
//         contentPane.add(label);

//         field.setFont(new Font("Tahoma", Font.PLAIN, 18));
//         field.setBounds(x + 160, y, 228, 40);
//         contentPane.add(field);
//     }

//     private void registerUser() {
//         String fName = firstname.getText();
//         String lName = lastname.getText();
//         String emailText = email.getText();
//         String user = username.getText();
//         String mobile = mob.getText();
//         String pass = new String(passwordField.getPassword());

//         if (fName.isEmpty() || lName.isEmpty() || emailText.isEmpty() || user.isEmpty() || mobile.isEmpty() || pass.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "❌ Please fill all fields.");
//             return;
//         }

//         if (mobile.length() != 10 || !mobile.matches("\\d+")) {
//             JOptionPane.showMessageDialog(this, "❌ Enter a valid 10-digit mobile number.");
//             return;
//         }

//         try (Connection con = DBConnection.getConnection()) {
//             if (con == null) {
//                 JOptionPane.showMessageDialog(this, "❌ Database connection failed.");
//                 return;
//             }

//             String query = "INSERT INTO account (firstname, lastname, username, password, email, mobile) VALUES (?, ?, ?, ?, ?, ?)";
//             PreparedStatement ps = con.prepareStatement(query);
//             ps.setString(1, fName);
//             ps.setString(2, lName);
//             ps.setString(3, user);
//             ps.setString(4, pass);
//             ps.setString(5, emailText);
//             ps.setString(6, mobile);

//             int rows = ps.executeUpdate();
//             if (rows > 0) {
//                 JOptionPane.showMessageDialog(this, "✅ Account created successfully for " + fName + "!");
//                 clearFields();
//             } else {
//                 JOptionPane.showMessageDialog(this, "❌ Failed to create account.");
//             }

//         } catch (SQLIntegrityConstraintViolationException e) {
//             JOptionPane.showMessageDialog(this, "❌ Username already exists.");
//         } catch (SQLException e) {
//             JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage());
//         }
//     }

//     private void clearFields() {
//         firstname.setText("");
//         lastname.setText("");
//         email.setText("");
//         username.setText("");
//         mob.setText("");
//         passwordField.setText("");
//     }
// }
