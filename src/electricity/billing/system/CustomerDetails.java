package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerDetails extends JFrame implements ActionListener {
    Choice searchMeterChoice, searchNameChoice;
    JTable table;
    JButton searchBtn, printBtn, closeBtn;

    public CustomerDetails() {
        super("Customer Details");
        getContentPane().setBackground(new Color(192, 186, 254));
        setSize(700, 500);
        setLocation(480, 200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search by meter number");
        searchMeter.setBounds(20, 20, 150, 20);
        add(searchMeter);

        searchMeterChoice = new Choice();
        searchMeterChoice.setBounds(180, 20, 150, 20);
        add(searchMeterChoice);

        try {
            MySqlConnection conn = new MySqlConnection();
            String query = "select * from New_Customer";
            ResultSet resultSet = conn.statement.executeQuery(query);

            while(resultSet.next()) {
                searchMeterChoice.add(resultSet.getString("meter_no"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        JLabel searchName = new JLabel("Search by Name");
        searchName.setBounds(380, 20, 100, 20);
        add(searchName);

        searchNameChoice = new Choice();
        searchNameChoice.setBounds(500, 20, 150, 20);
        add(searchNameChoice);

        try {
            MySqlConnection conn = new MySqlConnection();
            String query = "select * from New_Customer";
            ResultSet resultSet = conn.statement.executeQuery(query);

            while(resultSet.next()) {
                searchMeterChoice.add(resultSet.getString("name"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        table = new JTable();
        try {
            MySqlConnection conn = new MySqlConnection();
            String query = "select * from New_Customer";
            ResultSet resultSet = conn.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception E) {
            E.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100,700,500);
        scrollPane.setBackground(Color.white);
        add(scrollPane);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(20, 70, 110, 35);
        searchBtn.setBackground(new Color(228, 223, 223));
        searchBtn.setForeground(Color.BLACK);
        searchBtn.addActionListener(this);
        add(searchBtn);

        printBtn = new JButton("Print");
        printBtn.setBounds(120, 70, 110, 35);
        printBtn.setBackground(new Color(228, 223, 223));
        printBtn.setForeground(Color.BLACK);
        printBtn.addActionListener(this);
        add(printBtn);

        closeBtn = new JButton("Close");
        closeBtn.setBounds(600, 70, 110, 35);
        closeBtn.setBackground(new Color(228, 223, 223));
        closeBtn.setForeground(Color.BLACK);
        closeBtn.addActionListener(this);
        add(closeBtn);

        setVisible(true);
    }
    public static void main(String[] args) {
        new CustomerDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchBtn) {

            try {
                MySqlConnection conn = new MySqlConnection();
                String query_search = "select * from Bill where meter_no = '"+searchMeterChoice.getSelectedItem()+"' and month = '"+searchNameChoice.getSelectedItem()+"'";
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
