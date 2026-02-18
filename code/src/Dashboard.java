// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// public class Dashboard extends JFrame {
//     public Dashboard(String fullName, String role) {
//         setTitle("Welcome " + fullName + " (" + role + ")");
//         setSize(400, 300);
//         setLocationRelativeTo(null);
//         setLayout(new GridLayout(5, 1, 10, 10));

//         JLabel title = new JLabel("Dashboard", SwingConstants.CENTER);
//         title.setFont(new Font("Arial", Font.BOLD, 18));
//         add(title);

//         // Admin buttons
//         if (role.equalsIgnoreCase("admin")) {
//             JButton addEventBtn = new JButton("➕ Add Event");
//             JButton viewBookingsBtn = new JButton("👁️ View All Bookings");

//             add(addEventBtn);
//             add(viewBookingsBtn);

//             addEventBtn.addActionListener(e -> {
//                 new Add_events(); // 🟢 Open Add Event form
//             });

//             viewBookingsBtn.addActionListener(e -> {
//                 new ViewBookings(role, fullName); // 🟢 Open bookings viewer
//             });

//         } else {
//             // User buttons
//             JButton bookBtn = new JButton("🎟️ Book Ticket");
//             JButton myBookingsBtn = new JButton("👁️ View My Bookings");

//             add(bookBtn);
//             add(myBookingsBtn);

//             bookBtn.addActionListener(e -> {
//                 new Userlogin(fullName); // 🟢 Open booking window with user name
//             });

//             myBookingsBtn.addActionListener(e -> {
//                 new ViewBookings(role, fullName); // 🟢 Show only user's bookings
//             });
//         }

//         JButton logoutBtn = new JButton("Logout");
//         add(logoutBtn);
//         logoutBtn.addActionListener(e -> {
//             this.dispose();
//             new LoginWindow(); // Return to login screen
//         });

//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setVisible(true);
//     }
// }
