package electricity.billing.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentBill extends JFrame implements ActionListener {
    JButton backBtn;
    String meter;
    PaymentBill(String meter){
        this.meter = meter;
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try{
            j.setPage("https://paytm.com/online-payments");
            j.setBounds(400,150,800,600);
        }catch (Exception E){
            E.printStackTrace();
            j.setContentType("text/html");
            j.setText("<html>Error!</html>");
        }

        JScrollPane pane = new JScrollPane(j);
        add(pane);

        backBtn = new JButton("Back");
        backBtn.setBounds(640,20,80,30);
        backBtn.addActionListener(this);
        j.add(backBtn);


        setSize(800,600);
        setLocation(400,150);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new PayBill(meter);
    }

    public static void main(String[] args) {
        new PaymentBill("");
    }
}
