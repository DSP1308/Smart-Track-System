package Frontend_java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardWindow {
    public DashboardWindow(String username) {
        JFrame frame = new JFrame("Smart Track System - Dashboard");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ---- Top bar ----
        JLabel title = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        frame.add(title, BorderLayout.NORTH);

        // ---- Center panel ----
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton classBtn = new JButton("📚 Sync Google Classroom");
        JButton calBtn = new JButton("🗓️ View Google Calendar");
        JButton timeBtn = new JButton("🕒 View Timetable");
        JButton notifBtn = new JButton("🔔 View Reminders");
        centerPanel.add(classBtn);
        centerPanel.add(calBtn);
        centerPanel.add(timeBtn);
        centerPanel.add(notifBtn);
        frame.add(centerPanel, BorderLayout.CENTER);

        // ---- Bottom logout ----
        JButton logoutBtn = new JButton("Logout");
        frame.add(logoutBtn, BorderLayout.SOUTH);

        // ---- Button actions ----
        logoutBtn.addActionListener(e -> {
            frame.dispose();
            new LoginWindow();
        });

        frame.setVisible(true);
    }

    // Run standalone for testing
    public static void main(String[] args) {
        new DashboardWindow("Devi");
    }
}
