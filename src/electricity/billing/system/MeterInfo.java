package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener {

    Choice meterLocationChoice;
    Choice meterTypeChoice;
    Choice phaseCodeChoice;
    Choice billTypeChoice;
    JButton submitBtn;
    String meterNumber;
    JLabel meterNumberText, meterLocation, meterType;

    public MeterInfo(String meterNumber) {
        this.meterNumber = meterNumber;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(185, 211, 175));
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10,200, 20);
        heading.setFont(new Font("monospaced", Font.BOLD, 25));
        panel.add(heading);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(50, 80,200, 20);
        panel.add(meterNo);

        meterNumberText = new JLabel(meterNumber);
        meterNumberText.setBounds(180, 80,150,20);
        panel.add(meterNumberText);

        meterLocation = new JLabel("Meter Location");
        meterLocation.setBounds(50, 120,100, 20);
        panel.add(meterLocation);

        meterLocationChoice = new Choice();
        meterLocationChoice.add("Outside");
        meterLocationChoice.add("Inside");
        meterLocationChoice.setBounds(180, 120, 150, 20);
        panel.add(meterLocationChoice);

        meterType = new JLabel("Meter Type");
        meterType.setBounds(50, 160,100, 20);
        panel.add(meterType);

        meterTypeChoice = new Choice();
        meterTypeChoice.add("Electric Meter");
        meterTypeChoice.add("Solar Meter");
        meterTypeChoice.add("Smart Meter");
        meterTypeChoice.setBounds(180, 160, 100, 20);
        panel.add(meterTypeChoice);

        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50, 200,100, 20);
        panel.add(phaseCode);

        phaseCodeChoice = new Choice();
        phaseCodeChoice.add("011");
        phaseCodeChoice.add("022");
        phaseCodeChoice.add("033");
        phaseCodeChoice.add("044");
        phaseCodeChoice.add("055");
        phaseCodeChoice.add("066");
        phaseCodeChoice.add("077");
        phaseCodeChoice.add("088");
        phaseCodeChoice.add("099");
        phaseCodeChoice.setBounds(180, 200, 100, 20);
        panel.add(phaseCodeChoice);

        JLabel billType = new JLabel("Bill Type");
        billType.setBounds(50, 240,100, 20);
        panel.add(billType);

        billTypeChoice = new Choice();
        billTypeChoice.add("Normal");
        billTypeChoice.add("Industrial");
        billTypeChoice.setBounds(180, 240, 100, 20);
        panel.add(billTypeChoice);

        JLabel day = new JLabel("30 Days Billing Time...");
        day.setBounds(50, 280,150, 20);
        panel.add(day);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50, 320,100, 20);
        panel.add(note);

        JLabel note1 = new JLabel("By default bill is calculated for 30 days only");
        note1.setBounds(50, 340,300, 20);
        panel.add(note1);

        submitBtn = new JButton("Next");
        submitBtn.setBounds(180, 380, 110, 35);
        submitBtn.addActionListener(this);
        panel.add(submitBtn);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/details.png"));
        Image image = imageIcon.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIconNew = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIconNew);
        add(imageLabel, "East");

        setSize(800, 500);
        setLocation(900, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MeterInfo("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitBtn) {
            String query_meterInfo = getString();
            try {
                MySqlConnection conn = new MySqlConnection();
                conn.statement.executeUpdate(query_meterInfo);

                JOptionPane.showMessageDialog(null, "Meter information submitted successfully");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }

    private String getString() {
        String smeterNo = meterNumber;
        String smeterLocationChoice = meterLocationChoice.getSelectedItem();
        String smeterTypeChoice = meterTypeChoice.getSelectedItem();
        String sphaseCode = phaseCodeChoice.getSelectedItem();
        String sbillType = billTypeChoice.getSelectedItem();
        String sday = "30";

        return "insert into Meter_Info values('"+smeterNo+"', '"+smeterLocationChoice+"', '"+smeterTypeChoice+"', '"+sphaseCode+"', '"+sbillType+"', '"+sday+"')";
    }
}
