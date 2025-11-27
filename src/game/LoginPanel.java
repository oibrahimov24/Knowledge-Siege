package game;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Login/Register window class
 */
public class LoginPanel extends JPanel {
    /**
     * The main game
     */
    private KnowledgeSiege game;
    
    /**
     * TextField for the username of the player
     */
    private JTextField usernameField;
    
    /**
     * TextField for the password of the player
     */
    private JPasswordField passwordField;
    
    /**
     * Login button
     */
    private JButton loginButton;
    
    /**
     * Register button
     */
    private JButton registerButton;
    
    /**
     * ScoreBoard button
     */
    private JButton scoreBoardButton;
    
    /**
     * The file with the information about users
     */
    private static final String USERS_FILE="users.txt";
    
    private JPanel panel;

    /**
     * Constructor for the LoginPanel
     * @param game
     */
    public LoginPanel(KnowledgeSiege game) {
        this.game=game;
        setLayout(new GridLayout(5,2,10,10));

        usernameField=new JTextField(15);
        passwordField=new JPasswordField(15);
        loginButton=new JButton("Login");
        registerButton=new JButton("Register");
        scoreBoardButton=new JButton("ScoreBoard");
        

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);
        add(scoreBoardButton);

        loginButton.addActionListener(e->login());
        registerButton.addActionListener(e->{
            try {
                register();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        scoreBoardButton.addActionListener(e->showScoreBoard());
    }

    /**
     * Method for the user login
     */
    private void login() {
        String username=usernameField.getText();
        String password=new String(passwordField.getPassword());
        try {
            if (username.isEmpty()||password.isEmpty()) {
                throw new GameException("Username or password is empty");
            }
            if (authenticate(username,password)) {
                game.startGame(username);
            } else {
                JOptionPane.showMessageDialog(this,"Invalid username or password","Login Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (GameException e) {
            JOptionPane.showMessageDialog(this,e.getMessage(),"Login Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method for user registration
     * @throws IOException
     */
    private void register() throws IOException {
        String username=usernameField.getText().trim();
        String password=new String(passwordField.getPassword()).trim();
        try {
            if (username.isEmpty()||password.isEmpty()) {
                throw new GameException("Username or password is empty");
            }
            if (username.contains("|")) {
                throw new GameException("Username cannot contain the '|' character");
            }
            if (authenticate(username,null)) {
                throw new GameException("Username already exists");
            }
            try (java.io.FileWriter fw=new java.io.FileWriter(USERS_FILE,true)) {
                fw.write(username+"|"+password+"\n");
            } catch (FileNotFoundException e) {
                throw new GameException(e.getMessage());
            }
            JOptionPane.showMessageDialog(this,"You registered. Now log in to play the game!","Yahoo!",JOptionPane.INFORMATION_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
        } catch (GameException e) {
            JOptionPane.showMessageDialog(this,e.getMessage(),"Registration Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method for checking if the Username and Password exist and match each other
     * @param username
     * @param password
     * @return
     * @throws GameException
     */
    private boolean authenticate(String username,String password) throws GameException {
        try (Scanner scanner=new Scanner(new File(USERS_FILE))) {
            while (scanner.hasNextLine()) {
                String line=scanner.nextLine();
                String[] parts=line.split("\\|");
                if (parts.length>=2&&parts[0].equals(username)) {
                    if (password==null||parts[1].equals(password)) {
                        return true;
                    }
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }

    /**
     * Method for showing the ScoreBoard
     */
    private void showScoreBoard() {
        StringBuilder scoreContent=new StringBuilder();
        try (Scanner scanner=new Scanner(new File(ScoreBoard.SCORES_FILE))) {
            while (scanner.hasNextLine()) {
                scoreContent.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            scoreContent.append("Score file not found. Play and win to record scores!");
        }
        JOptionPane.showMessageDialog(this,new JScrollPane(new JTextArea(scoreContent.toString())),"Scoreboard",JOptionPane.INFORMATION_MESSAGE);
    }
}
