package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GenerateBill extends JFrame implements ActionListener {
    Choice searchMonthChoice;
    String meter;
    JTextArea area;
    JButton billBtn;
    GenerateBill(String meter){
        this.meter = meter;
        setSize(500,700);
        setLocation(500,30);
        setLayout( new BorderLayout());
        JPanel panel = new JPanel();

        JLabel heading = new JLabel("Generate Bill");

        JLabel meter_no = new JLabel(meter);

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

        area= new JTextArea(50,15);
        area.setText("\n \n \t ------------------- Click on the ---------------\n \t ------------------- Generate Bill");
        area.setFont(new Font("Senserif",Font.ITALIC,15));
        JScrollPane pane = new JScrollPane(area);
        billBtn = new JButton("Generate Bill");
        billBtn.addActionListener(this);

        add(pane);

        panel.add(heading);
        panel.add(meter_no);
        panel.add(searchMonthChoice);
        add(panel,"North");
        add(billBtn,"South");

        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try{
            MySqlConnection c = new MySqlConnection();
            String smonth =  searchMonthChoice.getSelectedItem();
            area.setText("\n Power Limited \n Electricity Bill For Month of "+smonth+",2023\n\n\n");
            ResultSet resultSet = c.statement.executeQuery("select * from New_Customer where meter_no ='"+meter+"'");
            if (resultSet.next()){
                area.append("\n    Customer Name        : "+resultSet.getString("name"));
                area.append("\n    Customer Meter Number: "+resultSet.getString("meter_no"));
                area.append("\n    Customer Address     : "+resultSet.getString("address"));
                area.append("\n    Customer City        : "+resultSet.getString("city"));
                area.append("\n    Customer State       : "+resultSet.getString("state"));
                area.append("\n    Customer Email       : "+resultSet.getString("email"));
                area.append("\n    Customer Phone Number       : "+resultSet.getString("phone_no"));

            }

            resultSet = c.statement.executeQuery("select * from Meter_Info where meter_no ='"+meter+"'");
            if (resultSet.next()){
                area.append("\n    Customer Meter Location        : "+resultSet.getString("meter_location"));
                area.append("\n    Customer Meter Type            : "+resultSet.getString("meter_type"));
                area.append("\n    Customer Phase Code            : "+resultSet.getString("phase_code"));
                area.append("\n    Customer Bill Type             : "+resultSet.getString("bill_type"));
                area.append("\n    Customer Days                  : "+resultSet.getString("Days"));


            }
            resultSet = c.statement.executeQuery("select * from Tax");
            if (resultSet.next()){
                area.append("\n   Cost Per Unit        : "+resultSet.getString("cost_per_unit"));
                area.append("\n   Meter Rent           : "+resultSet.getString("meter_rent"));
                area.append("\n   Service Charge       : "+resultSet.getString("service_charge"));
                area.append("\n   Service Tax          : "+resultSet.getString("service_tax"));
                area.append("\n   Swacch Bharat        : "+resultSet.getString("swacch_bharat"));
                area.append("\n   Fixed Tax            : "+resultSet.getString("fixed_tax"));

            }
            resultSet = c.statement.executeQuery("select * from Bill where meter_no = '"+meter+"' and month = '"+searchMonthChoice.getSelectedItem()+"'");
            if (resultSet.next()) {
                area.append("\n    Current Month       : "+resultSet.getString("month"));
                area.append("\n   Units Consumed       : "+resultSet.getString("unit"));
                area.append("\n   Total Charges        : "+resultSet.getString("total_bill"));
                area.append("\n   Total Payable        : "+resultSet.getString("total_bill"));
            }


        }catch (Exception E ){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
