package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewInformation extends JFrame implements ActionListener {
    String view;
    JButton cancelBtn;
    public ViewInformation(String meterPass) {
        System.out.println("Meter number: " + meterPass);
        this.view = meterPass;

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(220, 10, 500, 40);
        heading.setFont(new Font("monospaced", Font.BOLD, 25));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(80, 80, 100, 20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setForeground(Color.black);
        nameText.setBounds(200, 80, 150, 20);
        add(nameText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(80, 120, 100, 20);
        add(meterNo);

        JLabel meterNoText = new JLabel("");
        meterNoText.setBounds(200, 120, 150, 20);
        add(meterNoText);

        JLabel address = new JLabel("Address");
        address.setBounds(80, 160, 100, 20);
        add(address);

        JLabel addressText = new JLabel("");
        addressText.setBounds(200, 160, 150, 20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(80, 200, 100, 20);
        add(city);

        JLabel cityText = new JLabel("");
        cityText.setBounds(200, 200, 150, 20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(450, 80, 100, 20);
        add(state);

        JLabel stateText = new JLabel("");
        stateText.setBounds(550, 80, 150, 20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(450, 120, 100, 20);
        add(email);

        JLabel emailText = new JLabel("");
        emailText.setBounds(550, 120, 150, 20);
        add(emailText);

        JLabel phoneNo = new JLabel("Phone Number");
        phoneNo.setBounds(450, 160, 100, 20);
        add(phoneNo);

        JLabel phoneNoText = new JLabel("");
        phoneNoText.setBounds(550, 160, 150, 20);
        add(phoneNoText);

        try {
            MySqlConnection conn = new MySqlConnection();
            String query = "select * from New_Customer where meter_no = '"+view+"'";
            ResultSet resultSet = conn.statement.executeQuery(query);

            if(resultSet.next()) {
                nameText.setText(resultSet.getString("name"));
                meterNoText.setText(resultSet.getString("meter_no"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneNoText.setText(resultSet.getString("phone_no"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(340, 250, 110, 35);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/viewInfo.png"));
        Image image = imageIcon.getImage().getScaledInstance(800, 240, Image.SCALE_DEFAULT);
        ImageIcon imageIconNew = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIconNew);
        imageLabel.setBounds(0, 285, 800, 240);
        add(imageLabel);

        setSize(800, 550);
        setLocation(900, 400);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ViewInformation("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelBtn) {
            setVisible(false);
        }
    }
}