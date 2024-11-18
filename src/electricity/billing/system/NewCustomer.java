package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {
    JLabel heading;
    TextField nameText;
    JLabel customerName;
    JLabel meterNumber;
    JLabel meterText;
    JLabel address;
    TextField addressText;
    JLabel city;
    TextField cityText;
    JLabel state;
    TextField stateText;
    JLabel email;
    TextField emailText;
    JLabel phoneNo;
    TextField phoneNoText;
    JButton cancelBtn;
    JButton nextBtn;

    public NewCustomer() {
        super("New Customer");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 220, 200));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(220, 15, 220,20);
        heading.setFont(new Font("monospaced", Font.BOLD, 20));
        panel.add(heading);

        customerName = new JLabel("Customer Name");
        customerName.setBounds(100, 80, 150,20);
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(260, 80, 150, 20);
        panel.add(nameText);

        meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(100, 120, 150,20);
        panel.add(meterNumber);

        meterText = new JLabel("");
        meterText.setBounds(260, 120, 180, 25);
        panel.add(meterText);

        Random randomNum = new Random();
        long number = randomNum.nextLong() % 1000000;
        meterText.setText(""+Math.abs(number));

        address = new JLabel("Address");
        address.setBounds(100, 160, 150,20);
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(260, 160, 150, 20);
        panel.add(addressText);

        city = new JLabel("City");
        city.setBounds(100, 200, 150,20);
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(260, 200, 150, 20);
        panel.add(cityText);

        state = new JLabel("State");
        state.setBounds(100, 240, 150,20);
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(260, 240, 150, 20);
        panel.add(stateText);

        email = new JLabel("Email");
        email.setBounds(100, 280, 150,20);
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(260, 280, 150, 20);
        panel.add(emailText);

        phoneNo = new JLabel("Phone Number");
        phoneNo.setBounds(100, 320, 150,20);
        panel.add(phoneNo);

        phoneNoText = new TextField();
        phoneNoText.setBounds(260, 320, 150, 20);
        panel.add(phoneNoText);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(120, 390, 110, 35);
        nextBtn.setBackground(new Color(228, 223, 223));
        nextBtn.setForeground(Color.BLACK);
        nextBtn.addActionListener(this);
        panel.add(nextBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(260, 390, 110, 35);
        cancelBtn.setBackground(new Color(228, 223, 223));
        cancelBtn.setForeground(Color.BLACK);
        cancelBtn.addActionListener(this);
        panel.add(cancelBtn);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/boy.png"));
        Image image = imageIcon.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIconNew = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIconNew);
        add(imageLabel, "West");

        setSize(800, 500);
        setLocation(900, 400);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextBtn) {
            String name = nameText.getText();
            String meterNumber = meterText.getText();
            String address = addressText.getText();
            String city = cityText.getText();
            String state = stateText.getText();
            String email = emailText.getText();
            String phoneNo = phoneNoText.getText();

            String query_customer = "insert into New_Customer values('"+name+"', '"+meterNumber+"', '"+address+"', '"+city+"', '"+state+"', '"+email+"', '"+phoneNo+"')";
            String query_signup = "insert into SignUp values('"+meterNumber+"', '', '"+name+"', '', '')";

            try {
                MySqlConnection conn = new MySqlConnection();
                conn.statement.executeUpdate(query_customer);
                conn.statement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null, "Customer details added successfully");
                setVisible(false);
                new MeterInfo(meterNumber);
            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if(e.getSource() == cancelBtn) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
