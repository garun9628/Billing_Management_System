package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class BillDetails extends JFrame {
    String meter;
    BillDetails(String meter){
        this.meter =meter;
        setSize(800, 550);
        setLocation(900, 400);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable();

        try{
            MySqlConnection c = new MySqlConnection();
            String query_bill = "select * from Bill where meter_no = '"+meter+"'";
            ResultSet resultSet = c.statement.executeQuery(query_bill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,0,800,550);
        add(sp);

        setVisible(true);
    }
    public static void main(String[] args) {
        new BillDetails("");
    }
}
