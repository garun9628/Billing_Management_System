package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener {
    Choice loginByChoice;
    TextField meterText;
    TextField employerId;
    TextField usernameText;
    TextField nameText;
    TextField passwordText;
    JButton createBtn;
    JButton backBtn;

    public SignUp() {
        super("Signup Page");

        JLabel createAs = new JLabel("Create Account as");
        createAs.setBounds(40, 50, 125, 20);
        add(createAs);

        loginByChoice = new Choice();
        loginByChoice.add("Admin");
        loginByChoice.add("Customer");
        loginByChoice.setBounds(180, 50, 125, 20);
        add(loginByChoice);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(40, 100, 125, 20);
        meterNumber.setVisible(false);
        add(meterNumber);

        meterText = new TextField();
        meterText.setBounds(180, 100, 125, 20);
        meterNumber.setVisible(false);
        add(meterText);

        JLabel employer = new JLabel("Employer Id");
        employer.setBounds(40, 100, 125, 20);
        employer.setVisible(true);
        add(employer);

        employerId = new TextField();
        employerId.setBounds(180, 100, 125, 20);
        employer.setVisible(true);
        add(employerId);

        JLabel username = new JLabel("UserName");
        username.setBounds(40, 150, 125, 20);
        add(username);

        usernameText = new TextField();
        usernameText.setBounds(180, 150, 125, 20);
        add(usernameText);

        JLabel name = new JLabel("Name");
        name.setBounds(40, 200, 125, 20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(180, 200, 125, 20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    MySqlConnection conn = new MySqlConnection();
                    ResultSet resultSet = conn.statement.executeQuery("select * from SignUp where meter_no = '"+meterText.getText()+"'");
                    if(resultSet.next()) {
                        nameText.setText(resultSet.getString("name"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(40, 250, 125, 20);
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(180, 250, 125, 20);
        add(passwordText);


        loginByChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginByChoice.getSelectedItem();
                if(user.equals("Admin")) {
                    employer.setVisible(true);
                    employerId.setVisible(true);
                    meterNumber.setVisible(false);
                    meterText.setVisible(false);
                } else {
                    employer.setVisible(false);
                    nameText.setEditable(false);
                    employerId.setVisible(false);
                    meterNumber.setVisible(true);
                    meterText.setVisible(true);
                }
            }
        });

        createBtn = new JButton("Create");
        createBtn.setBackground(new Color(102, 145, 213));
        createBtn.setForeground(Color.black);
        createBtn.setBounds(60, 320, 100, 20);
        createBtn.addActionListener(this);
        add(createBtn);

        backBtn = new JButton("Back");
        backBtn.setBackground(new Color(102, 145, 213));
        backBtn.setForeground(Color.black);
        backBtn.setBounds(180, 320, 100, 20);
        backBtn.addActionListener(this);
        add(backBtn);

        ImageIcon createUserIcon = new ImageIcon(ClassLoader.getSystemResource("icons/createUser.png"));
        Image createUserImg = createUserIcon.getImage().getScaledInstance(221, 228, Image.SCALE_DEFAULT);
        ImageIcon createUserIconTow = new ImageIcon(createUserImg);
        JLabel createUserLabel = new JLabel(createUserIconTow);
        createUserLabel.setBounds(340, 70, 221, 228);
        add(createUserLabel);


        setSize(600, 400);
        setLocation(900, 400);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new SignUp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createBtn) {
            // logic to create user in database
            String sLoginAs = loginByChoice.getSelectedItem();
            String sUsername = usernameText.getText();
            String sName = nameText.getText();
            String sPassword = passwordText.getText();
            String sMeterNumber = meterText.getText();

            try {
                MySqlConnection conn = new MySqlConnection();
                String query = null;
                if(loginByChoice.getSelectedItem().equals("Admin")) {
                    query = "insert into SignUp value('"+sMeterNumber+"', '"+sUsername+"', '"+sName+"', '"+sPassword+"', '"+sLoginAs+"')";
                } else {
                    query = "update SignUp set username = '"+sUsername+"', password = '"+sPassword+"', userType = '"+sLoginAs+"' where meter_no = '"+sMeterNumber+"'";
                }
                conn.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account created successfully");
                setVisible(false);
                new Login();

            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if (e.getSource() == backBtn) {
            setVisible(false);
            new Login();
        }
    }
}
