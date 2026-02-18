import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterForm extends JFrame {

    private JTextField fullNameField, usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;

    public RegisterForm() {
        setTitle("📝 Register");
        setBounds(450, 200, 450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        JLabel lblTitle = new JLabel("Create Account", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(100, 20, 250, 30);
        add(lblTitle);

        JLabel lblFullName = new JLabel("Full Name:");
        lblFullName.setBounds(50, 80, 100, 25);
        add(lblFullName);

        fullNameField = new JTextField();
        fullNameField.setBounds(160, 80, 200, 25);
        add(fullNameField);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 120, 100, 25);
        add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(160, 120, 200, 25);
        add(usernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 160, 100, 25);
        add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 160, 200, 25);
        add(passwordField);

        JLabel lblRole = new JLabel("Role:");
        lblRole.setBounds(50, 200, 100, 25);
        add(lblRole);

        roleCombo = new JComboBox<>(new String[] { "user", "admin" });
        roleCombo.setBounds(160, 200, 200, 25);
        add(roleCombo);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(160, 250, 100, 30);
        add(registerBtn);

        JButton loginBtn = new JButton("Back to Login");
        loginBtn.setBounds(270, 250, 130, 30);
        add(loginBtn);

        registerBtn.addActionListener(e -> registerUser());
        loginBtn.addActionListener(e -> {
            dispose();
            new Userlogin().setVisible(true);
        });
    }

    private void registerUser() {
        String fullName = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleCombo.getSelectedItem();

        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❗ All fields are required.");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(this, "⚠️ DB connection is null. Check DBConnection.java");
                return;
            }
            String query = "INSERT INTO users (full_name, username, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, fullName);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, role);

            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "✅ Registered successfully!");
                dispose();
                new Userlogin().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to register.");
            }
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠️ DB Error: " + ex.getMessage());
        }
    }
}
