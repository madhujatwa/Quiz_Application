package com.quizapplication;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginSignup extends JFrame implements ActionListener {

    JTextField nameField, emailField;
    JPasswordField passwordField;
    JButton signupBtn, loginBtn, switchToSignup;
    JPanel panel;
    boolean isLogin = true;

    public LoginSignup() {
        setTitle("Login / Signup");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 400);
        panel.setBackground(Color.white);
        add(panel);

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setBounds(180, 20, 200, 30);
        panel.add(title);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(70, 80, 100, 25);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 80, 250, 25);
        panel.add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(70, 130, 100, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 250, 25);
        panel.add(passwordField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(70, 180, 100, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 180, 250, 25);
        panel.add(nameField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 240, 100, 30);
        loginBtn.addActionListener(this);
        panel.add(loginBtn);

        signupBtn = new JButton("Signup");
        signupBtn.setBounds(250, 240, 100, 30);
        signupBtn.addActionListener(this);
        panel.add(signupBtn);

        switchToSignup = new JButton("New user? Signup");
        switchToSignup.setBounds(150, 290, 200, 25);
        switchToSignup.setBorderPainted(false);
        switchToSignup.setForeground(Color.BLUE);
        switchToSignup.setContentAreaFilled(false);
        switchToSignup.addActionListener(this);
        panel.add(switchToSignup);

        nameLabel.setVisible(false);
        nameField.setVisible(false);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupBtn && !isLogin) {
            String name = nameField.getText();
            String email = emailField.getText();
            String pass = new String(passwordField.getPassword());

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            try {
                Connection con = DBConnection.getConnection();
                String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, pass);
                ps.executeUpdate();
                ps.close();
                con.close();

                JOptionPane.showMessageDialog(this, "Signup successful! Now login.");
                switchToLoginForm();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }

        } else if (e.getSource() == loginBtn && isLogin) {
            String email = emailField.getText();
            String pass = new String(passwordField.getPassword());

            try {
                Connection con = DBConnection.getConnection();
                String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    JOptionPane.showMessageDialog(this, "Welcome " + name + "!");
                    setVisible(false);
                    new Rules(name);  // ✅ call Rules class
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials!");
                }

                rs.close();
                ps.close();
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }

        } else if (e.getSource() == switchToSignup) {
            if (isLogin) {
                isLogin = false;
                nameField.setVisible(true);
                panel.getComponent(0).setVisible(false);
                JLabel newTitle = new JLabel("Signup");
                newTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
                newTitle.setBounds(180, 20, 200, 30);
                panel.add(newTitle);
                switchToSignup.setText("Already have account? Login");
                panel.repaint();
            } else {
                switchToLoginForm();
            }
        }
    }

    private void switchToLoginForm() {
        isLogin = true;
        nameField.setVisible(false);
        nameField.setText("");
        panel.remove(0);
        JLabel newTitle = new JLabel("Login");
        newTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        newTitle.setBounds(180, 20, 200, 30);
        panel.add(newTitle, 0);
        switchToSignup.setText("New user? Signup");
        panel.repaint();
    }

    public static void main(String[] args) {
        new LoginSignup();
    }
}
