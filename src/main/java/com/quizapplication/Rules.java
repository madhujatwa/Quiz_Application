package com.quizapplication;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener {

    String name;
    JButton start, back;

    public Rules(String name) {
        this.name = name;

        setTitle("Quiz Rules");
        setSize(800, 650);
        setLocation(350, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Welcome " + name + " to Simple Minds");
        heading.setBounds(50, 20, 700, 35);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        heading.setForeground(new Color(30, 144, 145));
        add(heading);

        JLabel rules = new JLabel();
        rules.setBounds(20, 90, 700, 350);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
                "<html>" +
                        "1. You will get 10 questions in this quiz.<br><br>" +
                        "2. Each question has only one correct answer.<br><br>" +
                        "3. You get 10 points for each correct answer.<br><br>" +
                        "4. There is no negative marking.<br><br>" +
                        "5. Click 'Start' to begin the quiz.<br><br>" +
                        "6. Try to answer within the time limit.<br><br>" +
                        "</html>"
        );
        add(rules);

        start = new JButton("Start");
        start.setBounds(250, 500, 100, 30);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.white);
        start.addActionListener(this);
        add(start);

        back = new JButton("Back");
        back.setBounds(400, 500, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            setVisible(false);
            // Quiz page open karo, constructor mein name pass karo
            new Quiz(name); 
        } else if (e.getSource() == back) {
            setVisible(false);
            new LoginSignup();
        }
    }

    public static void main(String[] args) {
        new Rules("User");  // Testing
    }
}
