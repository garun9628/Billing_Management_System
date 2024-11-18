package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class PayBill extends JFrame implements ActionListener {
    Choice searchMonthChoice;
    String meter;
    JButton payBtn,backBtn;

    public PayBill(String meter){
        this.meter = meter;
        setBounds(900,400,800,550);
        setLayout(null);

        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("monospaced",Font.BOLD,25));
        heading.setBounds(220,10,400,30);
        add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50,80,200,20);
        add(meterNumber);


        JLabel meterNumberText = new JLabel("");
        meterNumberText.setBounds(300,80,200,20);
        add(meterNumberText);

        JLabel name = new JLabel("Name");
        name.setBounds(50,140,200,20);
        add(name);


        JLabel nameText = new JLabel("");
        nameText.setBounds(300,140,200,20);
        add(nameText);


        JLabel month = new JLabel("Month");
        month.setBounds(50,200,200,20);
        add(month);

        searchMonthChoice = new Choice();
        searchMonthChoice.add("January");
        searchMonthChoice.add("February");
        searchMonthChoice.add("March");
        searchMonthChoice.add("April");
        searchMonthChoice.add("May");
        searchMonthChoice.add("June");
        searchMonthChoice.add("July");
        searchMonthChoice.add("August");
        searchMonthChoice.add("September");
        searchMonthChoice.add("October");
        searchMonthChoice.add("November");
        searchMonthChoice.add("December");
        searchMonthChoice.setBounds(300,200,150,20);
        add(searchMonthChoice);

        JLabel unit = new JLabel("Unit");
        unit.setBounds(50,260,200,20);
        add(unit);


        JLabel unitText = new JLabel("");
        unitText.setBounds(300,260,200,20);
        add(unitText);

        JLabel totalBill = new JLabel("Total Bill");
        totalBill.setBounds(50,320,200,20);
        add(totalBill);


        JLabel totalBillText = new JLabel("");
        totalBillText.setBounds(300,320,200,20);
        add(totalBillText);


        JLabel status = new JLabel("Status");
        status.setBounds(50,380,200,20);
        add(status);


        JLabel statusText = new JLabel("");
        statusText.setBounds(300,380,200,20);
        add(statusText);

        try{
            MySqlConnection c = new MySqlConnection();
            ResultSet resultSet = c.statement.executeQuery("select * from New_Customer where meter_no = '"+meter+"'");
            while (resultSet.next()){
                meterNumberText.setText(meter);
                nameText.setText(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        searchMonthChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                MySqlConnection c = new MySqlConnection();
                try{
                    ResultSet resultSet = c.statement.executeQuery("select * from Bill where meter_no = '"+meter+"' and month = '"+searchMonthChoice.getSelectedItem()+"'");
                    while (resultSet.next()){
                        unitText.setText(resultSet.getString("unit"));
                        totalBillText.setText(resultSet.getString("total_bill"));
                        statusText.setText(resultSet.getString("status"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        payBtn = new JButton("Pay");
        payBtn.setBounds(100,460,100,25);
        payBtn.addActionListener(this);
        add(payBtn);


        backBtn = new JButton("Back");
        backBtn.setBounds(230,460,100,25);
        backBtn.addActionListener(this);
        add(backBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payBtn){
            try{
                MySqlConnection c = new MySqlConnection();
                c.statement.executeUpdate("update Bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+searchMonthChoice.getSelectedItem()+"'");
            }catch (Exception E){
                E.printStackTrace();
            }
            setVisible(false);
            new PaymentBill(meter);
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
