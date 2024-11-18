package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {
    Choice searchMeterChoice, searchMonthChoice;
    JTable table;
    JButton searchBtn, printBtn, closeBtn;

    public DepositDetails() {
        super("Deposit Details");
        getContentPane().setBackground(new Color(192, 186, 254));
        setBounds(900,400,800,550);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search by Meter number");
        searchMeter.setBounds(20, 20, 150, 20);
        add(searchMeter);

        searchMeterChoice = new Choice();
        searchMeterChoice.setBounds(190, 20, 150, 20);
        add(searchMeterChoice);

        try {
            MySqlConnection conn = new MySqlConnection();
            String query = "select * from Bill";
            ResultSet resultSet = conn.statement.executeQuery(query);

            while(resultSet.next()) {
                searchMeterChoice.add(resultSet.getString("meter_no"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        JLabel searchName = new JLabel("Search by Month");
        searchName.setBounds(380, 20, 150, 20);
        add(searchName);

        searchMonthChoice = new Choice();
        searchMonthChoice.setBounds(555, 20, 150, 20);
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
        add(searchMonthChoice);

        table = new JTable();
        try {
            MySqlConnection conn = new MySqlConnection();
            String query = "select * from Bill";
            ResultSet resultSet = conn.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception E) {
            E.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100,800,450);
        scrollPane.setBackground(Color.white);
        add(scrollPane);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(20, 70, 110, 35);
        searchBtn.setBackground(new Color(228, 223, 223));
        searchBtn.setForeground(Color.BLACK);
        searchBtn.addActionListener(this);
        add(searchBtn);

        printBtn = new JButton("Print");
        printBtn.setBounds(150, 70, 110, 35);
        printBtn.setBackground(new Color(228, 223, 223));
        printBtn.setForeground(Color.BLACK);
        printBtn.addActionListener(this);
        add(printBtn);

        closeBtn = new JButton("Close");
        closeBtn.setBounds(650, 70, 110, 35);
        closeBtn.setBackground(new Color(228, 223, 223));
        closeBtn.setForeground(Color.BLACK);
        closeBtn.addActionListener(this);
        add(closeBtn);

        setVisible(true);
    }
    public static void main(String[] args) {
        new DepositDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchBtn) {

            try {
                MySqlConnection conn = new MySqlConnection();
                String query_search = "select * from New_Customer where meter_no = '"+searchMeterChoice.getSelectedItem()+"' and name = '"+searchMonthChoice.getSelectedItem()+"'";
                ResultSet resultSet = conn.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if(e.getSource() == "Print") {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
}
