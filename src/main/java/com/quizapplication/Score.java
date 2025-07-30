package com.quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Score extends JFrame implements ActionListener {

    public Score(String name, int score) {
        int totalMarks = 100;
        int correctAnswers = score / 10;
        int wrongAnswers = 10 - correctAnswers;

        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Thank you " + name + " for playing Simple Minds");
        heading.setBounds(45, 30, 700, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(heading);

        JLabel scoreLabel = new JLabel("Your Score: " + score + " / " + totalMarks);
        scoreLabel.setBounds(280, 150, 300, 30);
        scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(scoreLabel);

        JLabel correctLabel = new JLabel("Correct Answers: " + correctAnswers);
        correctLabel.setBounds(280, 200, 300, 30);
        correctLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        correctLabel.setForeground(new Color(34, 139, 34)); // Green
        add(correctLabel);

        JLabel wrongLabel = new JLabel("Wrong Answers: " + wrongAnswers);
        wrongLabel.setBounds(280, 250, 300, 30);
        wrongLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        wrongLabel.setForeground(Color.RED);
        add(wrongLabel);

        JButton playAgain = new JButton("Play Again");
        playAgain.setBounds(310, 320, 120, 35);
        playAgain.setBackground(new Color(30, 144, 254));
        playAgain.setForeground(Color.white);
        playAgain.addActionListener(this);
        add(playAgain);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new LoginSignup();
    }

    public static void main(String[] args) {
        new Score("User", 70); // for testing
    }
}
