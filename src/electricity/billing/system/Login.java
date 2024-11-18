package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField userText;
    JTextField passwordText;
    Choice loginChoice;
    JButton loginBtn;
    JButton cancelBtn;
    JButton signUpBtn;

    Login() {
        super("Login");

        getContentPane().setBackground(Color.white);

        JLabel username = new JLabel("UserName");
        username.setBounds(300, 60, 100, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        JLabel login = new JLabel("Login In As");
        login.setBounds(300, 140, 100, 20);
        add(login);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400, 140, 100, 20);
        add(loginChoice);


        loginBtn = new JButton("Login");
        loginBtn.setBounds(320, 180, 100, 20);
        loginBtn.addActionListener(this);
        add(loginBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(450, 180, 100, 20);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        signUpBtn = new JButton("Signup");
        signUpBtn.setBounds(385, 220, 100, 20);
        signUpBtn.addActionListener(this);
        add(signUpBtn);

        ImageIcon profileImg = new ImageIcon(ClassLoader.getSystemResource("icons/user.jpg"));
        Image profileTow = profileImg.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon usrProfileImg = new ImageIcon(profileTow);

        JLabel profileLabel = new JLabel(usrProfileImg);
        profileLabel.setBounds(10,10,250,250);
        add(profileLabel);


        setSize(600, 300);
        setLocation(950, 450);
        setLayout(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginBtn) {
            // logic to login user.
            String userName = userText.getText();
            String password = passwordText.getText();
            String userType = loginChoice.getSelectedItem();

            try {
                MySqlConnection conn = new MySqlConnection();
                String query = "select * from SignUp where username = '"+userName+"' and password = '"+password+"' and userType = '"+userType+"'";
                ResultSet resultSet = conn.statement.executeQuery(query);

                if(resultSet.next()) {
                    String meterNo = resultSet.getString("meter_no");
                    setVisible(false);
                    new MainPage(userType, meterNo);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login credentials");
                }
            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if (e.getSource() == signUpBtn) {
            setVisible(false);
            new SignUp();
        } else if (e.getSource() == cancelBtn) {
            setVisible(false);
        }
    }
}
