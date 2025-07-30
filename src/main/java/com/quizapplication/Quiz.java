package com.quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz extends JFrame implements ActionListener {
    String[][] questions = new String[10][5];
    String[][] answers = new String[10][2];
    String[] userAnswers = new String[10];

    JLabel qnoLabel, questionLabel, timerLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup optionsGroup;
    JButton nextBtn, submitBtn;

    public static int questionIndex = 0;
    public static int score = 0;
    Timer timer;
    int timeLeft = 120; // seconds

    public Quiz(String userName) {
        setBounds(200, 100, 900, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        qnoLabel = new JLabel();
        qnoLabel.setBounds(50, 20, 50, 30);
        qnoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(qnoLabel);

        questionLabel = new JLabel();
        questionLabel.setBounds(100, 20, 700, 30);
        questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(questionLabel);

        opt1 = new JRadioButton();
        opt1.setBounds(100, 80, 700, 30);
        opt1.setBackground(Color.WHITE);
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(100, 120, 700, 30);
        opt2.setBackground(Color.WHITE);
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(100, 160, 700, 30);
        opt3.setBackground(Color.WHITE);
        add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(100, 200, 700, 30);
        opt4.setBackground(Color.WHITE);
        add(opt4);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(opt1);
        optionsGroup.add(opt2);
        optionsGroup.add(opt3);
        optionsGroup.add(opt4);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(250, 300, 100, 30);
        nextBtn.addActionListener(this);
        add(nextBtn);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(400, 300, 100, 30);
        submitBtn.addActionListener(this);
        submitBtn.setEnabled(false);
        add(submitBtn);

        timerLabel = new JLabel("Time left: 2:00");
        timerLabel.setBounds(700, 20, 150, 30);
        timerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        setQuestions();
        loadQuestion();
        startTimer();

        setVisible(true);
    }

    public void setQuestions() {
        questions[0][0] = "What is the size of int in Java?";
        questions[0][1] = "2 bytes";
        questions[0][2] = "4 bytes";
        questions[0][3] = "8 bytes";
        questions[0][4] = "Depends on OS";
        answers[0][1] = "4 bytes";

        questions[1][0] = "Which keyword is used for inheritance in Java?";
        questions[1][1] = "this";
        questions[1][2] = "super";
        questions[1][3] = "extends";
        questions[1][4] = "implements";
        answers[1][1] = "extends";

        questions[2][0] = "Which method is called when an object is created?";
        questions[2][1] = "finalize()";
        questions[2][2] = "constructor";
        questions[2][3] = "start()";
        questions[2][4] = "main()";
        answers[2][1] = "constructor";

        questions[3][0] = "Which is not a Java feature?";
        questions[3][1] = "Object-Oriented";
        questions[3][2] = "Use of pointers";
        questions[3][3] = "Portable";
        questions[3][4] = "Dynamic";
        answers[3][1] = "Use of pointers";

        questions[4][0] = "Which of these is not an access modifier?";
        questions[4][1] = "public";
        questions[4][2] = "private";
        questions[4][3] = "protected";
        questions[4][4] = "volatile";
        answers[4][1] = "volatile";

        questions[5][0] = "Which collection doesn't allow duplicates?";
        questions[5][1] = "List";
        questions[5][2] = "Set";
        questions[5][3] = "Queue";
        questions[5][4] = "Map";
        answers[5][1] = "Set";

        questions[6][0] = "Which of these is not a loop in Java?";
        questions[6][1] = "for";
        questions[6][2] = "while";
        questions[6][3] = "loop";
        questions[6][4] = "do-while";
        answers[6][1] = "loop";

        questions[7][0] = "Which exception is thrown when array is out of bounds?";
        questions[7][1] = "NullPointerException";
        questions[7][2] = "ArrayIndexOutOfBoundsException";
        questions[7][3] = "ArithmeticException";
        questions[7][4] = "NumberFormatException";
        answers[7][1] = "ArrayIndexOutOfBoundsException";

        questions[8][0] = "Which class is parent of all classes in Java?";
        questions[8][1] = "Object";
        questions[8][2] = "Class";
        questions[8][3] = "Superclass";
        questions[8][4] = "Base";
        answers[8][1] = "Object";

        questions[9][0] = "What does JVM stand for?";
        questions[9][1] = "Java Virtual Method";
        questions[9][2] = "Java Verified Machine";
        questions[9][3] = "Java Virtual Machine";
        questions[9][4] = "Java Variable Memory";
        answers[9][1] = "Java Virtual Machine";
    }

    public void loadQuestion() {
        qnoLabel.setText((questionIndex + 1) + ".");
        questionLabel.setText(questions[questionIndex][0]);
        opt1.setText(questions[questionIndex][1]);
        opt2.setText(questions[questionIndex][2]);
        opt3.setText(questions[questionIndex][3]);
        opt4.setText(questions[questionIndex][4]);
        optionsGroup.clearSelection();
        timeLeft = 120;
    }

    public void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                int min = timeLeft / 60;
                int sec = timeLeft % 60;
                timerLabel.setText("Time left: " + min + ":" + String.format("%02d", sec));
                timeLeft--;

                if (timeLeft < 0) {
                    timer.cancel();
                    saveAnswer();
                    if (questionIndex == 9) {
                        submitQuiz();
                    } else {
                        questionIndex++;
                        loadQuestion();
                        startTimer();
                        if (questionIndex == 9) {
                            nextBtn.setEnabled(false);
                            submitBtn.setEnabled(true);
                        }
                    }
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextBtn) {
            saveAnswer();
            timer.cancel();
            questionIndex++;
            loadQuestion();
            startTimer();

            if (questionIndex == 9) {
                nextBtn.setEnabled(false);
                submitBtn.setEnabled(true);
            }
        } else if (e.getSource() == submitBtn) {
            saveAnswer();
            timer.cancel();
            submitQuiz();
        }
    }

    private void saveAnswer() {
        if (opt1.isSelected())
            userAnswers[questionIndex] = opt1.getText();
        else if (opt2.isSelected())
            userAnswers[questionIndex] = opt2.getText();
        else if (opt3.isSelected())
            userAnswers[questionIndex] = opt3.getText();
        else if (opt4.isSelected())
            userAnswers[questionIndex] = opt4.getText();
        else
            userAnswers[questionIndex] = ""; // No answer
    }

    private void submitQuiz() {
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] != null && userAnswers[i].equals(answers[i][1])) {
                score += 10;
            }
        }
        setVisible(false);
        new Score("User", score);
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}
