package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class CalculateBill extends JFrame implements ActionListener {
    TextField nameText, addressText, unitText;
    Choice meterNumChoice, monthChoice;
    JButton submitBtn, cancelBtn;
   public CalculateBill() {

       JPanel panel = new JPanel();
       panel.setLayout(null);
       panel.setBackground(new Color(200,190,220));
       add(panel);

       JLabel heading = new JLabel("Calculate Electricity Bill");
       heading.setBounds(50, 10, 400, 30);
       heading.setFont(new Font("monospaced", Font.BOLD, 25));
       panel.add(heading);

       JLabel meterNumber = new JLabel("Meter Number");
       meterNumber.setBounds(50, 80, 100, 20);
       panel.add(meterNumber);

       meterNumChoice = new Choice();
       try {
           MySqlConnection conn = new MySqlConnection();
           String query = "select * from New_Customer";
           ResultSet resultSet = conn.statement.executeQuery(query);

           while(resultSet.next()) {
               meterNumChoice.add(resultSet.getString("meter_no"));
           }
       } catch (Exception E) {
           E.printStackTrace();
       }
       meterNumChoice.setBounds(180, 80, 100, 20);
       panel.add(meterNumChoice);

       JLabel name = new JLabel("Name");
       name.setBounds(50,120,100,20);
       panel.add(name);

       nameText = new TextField("");
       nameText.setBounds(180, 120, 150, 20);
       panel.add(nameText);

       JLabel address = new JLabel("Address");
       address.setBounds(50, 160, 100,20);
       panel.add(address);

       addressText = new TextField("");
       addressText.setBounds(180, 160, 150, 20);
       panel.add(addressText);

       meterNumChoice.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               try {
                   MySqlConnection conn = new MySqlConnection();
                   String query = "select * from New_Customer where meter_no = '"+meterNumChoice.getSelectedItem()+"'";
                   ResultSet resultSet = conn.statement.executeQuery(query);

                   while(resultSet.next()) {
                       nameText.setText(resultSet.getString("name"));
                       addressText.setText(resultSet.getString("address"));
                   }
               } catch (Exception E) {
                   E.printStackTrace();
               }
           }
       });

       JLabel unitConsumed = new JLabel("Unit Consumed");
       unitConsumed.setBounds(50, 200, 100, 20);
       panel.add(unitConsumed);

       unitText = new TextField();
       unitText.setBounds(180, 200, 150, 20);
       panel.add(unitText);

       JLabel month = new JLabel("Month");
       month.setBounds(50, 240, 100, 20);
       panel.add(month);

       monthChoice = new Choice();
       monthChoice.add("January");
       monthChoice.add("February");
       monthChoice.add("March");
       monthChoice.add("April");
       monthChoice.add("May");
       monthChoice.add("June");
       monthChoice.add("July");
       monthChoice.add("August");
       monthChoice.add("September");
       monthChoice.add("October");
       monthChoice.add("November");
       monthChoice.add("December");

       monthChoice.setBounds(180, 240, 150,20);
       panel.add(monthChoice);

       submitBtn = new JButton("Submit");
       submitBtn.setBounds(100, 420, 110, 35);
       submitBtn.setBackground(new Color(228, 223, 223));
       submitBtn.setForeground(Color.BLACK);
       submitBtn.addActionListener(this);
       panel.add(submitBtn);

       cancelBtn = new JButton("Cancel");
       cancelBtn.setBounds(300, 420, 110, 35);
       cancelBtn.setBackground(new Color(228, 223, 223));
       cancelBtn.setForeground(Color.BLACK);
       cancelBtn.addActionListener(this);
       panel.add(cancelBtn);

       setLayout(new BorderLayout());
       add(panel, "Center");

       ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/budget.png"));
       Image image = imageIcon.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
       ImageIcon imageIconNew = new ImageIcon(image);
       JLabel imageLabel = new JLabel(imageIconNew);
       add(imageLabel, "East");

       setBounds(900,400,800,550);
       setVisible(true);
   }

    public static void main(String[] args) {
        new CalculateBill();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitBtn) {
            String smeterNo = meterNumChoice.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthChoice.getSelectedItem();

            int totalBill = 0;
            int units = Integer.parseInt(sunit);

            String query_tax = "select * from Tax";
            try {
                MySqlConnection conn = new MySqlConnection();
                ResultSet resultSet = conn.statement.executeQuery(query_tax);
                while(resultSet.next()) {
                    totalBill += units*Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("swacch_bharat_tax"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
            String query_total_bills = "insert into bill values('"+smeterNo+"', '"+smonth+"', '"+sunit+"', '"+totalBill+"', 'Not Paid')";

            try {
                MySqlConnection conn = new MySqlConnection();
                conn.statement.executeUpdate(query_total_bills);

                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }
}
