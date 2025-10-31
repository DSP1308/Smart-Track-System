package Frontend_java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Smart Track System - Login");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(150, 50, 150, 25);
        frame.add(userText);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 25);
        frame.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(150, 100, 150, 25);
        frame.add(passText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        frame.add(loginButton);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

       if (username.equals("admin") && password.equals("1234")) {
    JOptionPane.showMessageDialog(null, "Login Successful!");

    try {
        Backend_java.GoogleCalendarConnect.getCalendarService();
        JOptionPane.showMessageDialog(null, "Google Calendar Connected Successfully!");
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error connecting to Google Calendar: " + ex.getMessage());
    }

    try {
        Backend_java.ClassroomTasksFetcher.fetchTasks();
        JOptionPane.showMessageDialog(null, "Fetched Classroom Tasks Successfully!");
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error fetching tasks: " + ex.getMessage());
    }

    new DashboardWindow().setVisible(true);
    dispose();

} else {
    JOptionPane.showMessageDialog(null, "Invalid username or password!");
}


