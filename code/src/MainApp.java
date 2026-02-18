// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;

// public class MainApp extends JFrame {
//     private JPanel contentPane;

//     public MainApp() {
//         setTitle("🎟 Stadium Management");
//         setBounds(450, 200, 400, 300);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setResizable(false);

//         contentPane = new JPanel();
//         contentPane.setLayout(null);
//         setContentPane(contentPane);

//         JLabel lblTitle = new JLabel("Welcome to STDM", SwingConstants.CENTER);
//         lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
//         lblTitle.setBounds(60, 30, 280, 30);
//         contentPane.add(lblTitle);

//         JButton btnLogin = new JButton("👤 Login");
//         btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//         btnLogin.setBounds(120, 90, 150, 40);
//         contentPane.add(btnLogin);

//         JButton btnRegister = new JButton("📝 Register");
//         btnRegister.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//         btnRegister.setBounds(120, 150, 150, 40);
//         contentPane.add(btnRegister);

//         // ✅ Launch LoginWindow (not Userlogin)
//         btnLogin.addActionListener(e -> {
//             dispose();
//             new Userlogin().setVisible(true);  // LoginWindow handles authentication
//         });

//         // ✅ Launch RegisterForm (for new user sign-up)
//         btnRegister.addActionListener(e -> {
//             dispose();
//             new RegisterForm().setVisible(true);
//         });
//     }

//     public static void main(String[] args) {
//         EventQueue.invokeLater(() -> {
//             try {
//                 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                 new MainApp().setVisible(true);
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });
//     }
// }
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainApp extends JFrame {
    private JPanel contentPane;

    public MainApp() {
        setTitle("🎟 Stadium Management");
        setBounds(450, 200, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Welcome to STDM", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(60, 30, 280, 30);
        contentPane.add(lblTitle);

        JButton btnLogin = new JButton("👤 Login");
        btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnLogin.setBounds(120, 90, 150, 40);
        contentPane.add(btnLogin);

        JButton btnRegister = new JButton("📝 Register");
        btnRegister.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnRegister.setBounds(120, 150, 150, 40);
        contentPane.add(btnRegister);

        // ✅ Launch Userlogin (handles role-based redirection)
        btnLogin.addActionListener(e -> {
            dispose(); // close main window
            new Userlogin().setVisible(true);  // opens login screen
        });

        // ✅ Launch RegisterForm (for new user sign-up)
        btnRegister.addActionListener(e -> {
            dispose(); // close main window
            new RegisterForm().setVisible(true);
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new MainApp().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
