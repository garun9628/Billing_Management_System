package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame implements ActionListener {
    String accountType;
    String meterNo;

    public MainPage(String accountType, String meterNo) {
        this.accountType = accountType;
        this.meterNo = meterNo;
//        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/ebs.png"));
        Image img = imgIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon imgIconNew = new ImageIcon(img);
        JLabel imgLabel = new JLabel(imgIconNew);
        imgLabel.setBounds(0,0,1920,1080);
        add(imgLabel);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("serif", Font.BOLD, 20));

        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon customerIcon = new ImageIcon(ClassLoader.getSystemResource("icons/newcustomer.png"));
        Image customerImg = customerIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(customerImg));
        newCustomer.addActionListener(this);
        menu.add(newCustomer);

        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon customerDetailsIcon = new ImageIcon(ClassLoader.getSystemResource("icons/customerDetails.png"));
        Image customerDetailsImg = customerDetailsIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(customerDetailsImg));
        customerDetails.addActionListener(this);
        menu.add(customerDetails);

        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon depositDetailsIcon = new ImageIcon(ClassLoader.getSystemResource("icons/depositDetails.png"));
        Image depositDetailsImg = depositDetailsIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(depositDetailsImg));
        depositDetails.addActionListener(this);
        menu.add(depositDetails);

        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon calculateBillIcon = new ImageIcon(ClassLoader.getSystemResource("icons/calculatorbills.png"));
        Image calculateBillImg = calculateBillIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(calculateBillImg));
        calculateBill.addActionListener(this);
        menu.add(calculateBill);

        JMenu info = new JMenu("Information");
        info.setFont(new Font("serif", Font.BOLD, 20));

        JMenuItem updateInfo = new JMenuItem("Update Information");
        updateInfo.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon updateInfoIcon = new ImageIcon(ClassLoader.getSystemResource("icons/refresh.png"));
        Image updateInfoImg = updateInfoIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(updateInfoImg));
        updateInfo.addActionListener(this);
        info.add(updateInfo);

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon viewInfoIcon = new ImageIcon(ClassLoader.getSystemResource("icons/information.png"));
        Image viewInfoImg = viewInfoIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(viewInfoImg));
        viewInfo.addActionListener(this);
        info.add(viewInfo);

        JMenu user = new JMenu("User");
        user.setFont(new Font("serif", Font.BOLD, 20));

        JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon payBillIcon = new ImageIcon(ClassLoader.getSystemResource("icons/pay.png"));
        Image payBillImg = payBillIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(payBillImg));
        payBill.addActionListener(this);
        user.add(payBill);

        JMenuItem billDetails = new JMenuItem("Bill Details");
        billDetails.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon billDetailsIcon = new ImageIcon(ClassLoader.getSystemResource("icons/payDetail.png"));
        Image billDetailsImg = billDetailsIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(billDetailsImg));
        billDetails.addActionListener(this);
        user.add(billDetails);

        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("serif", Font.BOLD, 20));

        JMenuItem generateBill = new JMenuItem("Pay Information");
        generateBill.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon generateBillIcon = new ImageIcon(ClassLoader.getSystemResource("icons/bill.png"));
        Image generateBillImg = generateBillIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        generateBill.setIcon(new ImageIcon(generateBillImg));
        generateBill.addActionListener(this);
        bill.add(generateBill);

        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("serif", Font.BOLD, 20));

        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image notepadImg = notepadIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(notepadImg));
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon calculatorIcon = new ImageIcon(ClassLoader.getSystemResource("icons/calculator.png"));
        Image calculatorImg = calculatorIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(calculatorImg));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("serif", Font.BOLD, 20));

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setFont(new Font("monospaced", Font.PLAIN, 18));
        ImageIcon exitMenuItemIcon = new ImageIcon(ClassLoader.getSystemResource("icons/exit.png"));
        Image exitMenuItemImg = exitMenuItemIcon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        exitMenuItem.setIcon(new ImageIcon(exitMenuItemImg));
        exitMenuItem.addActionListener(this);
        exit.add(exitMenuItem);

        if(accountType == "Admin") {
            menuBar.add(menu);
        } else {
            menuBar.add(bill);
            menuBar.add(user);
            menuBar.add(info);
        }

        menuBar.add(utility);
        menuBar.add(exit);

//        setLayout(new FlowLayout());
        setLayout(new BorderLayout());
        setSize(1920, 1080);
        setLocation(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainPage("", "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if(msg.equals("New Customer")) {
            new NewCustomer();
        } else if(msg.equals("Customer Details")) {
            new CustomerDetails();
        } else if(msg.equals("Deposit Details")) {
            new DepositDetails();
        } else if(msg.equals("Calculate Bill")) {
            new CalculateBill();
        } else if(msg.equals("Update Information")) {
            new UpdateInformation(meterNo);
        } else if(msg.equals("View Information")) {
            new ViewInformation(meterNo);
        } else if(msg.equals("Bill Details")) {
            new BillDetails(meterNo);
        } else if(msg.equals("Pay Bill")) {
            new PayBill(meterNo);
        } else if(msg.equals("Generate Bill")) {
            new GenerateBill(meterNo);
        } else if(msg.equals("Notepad")) {
            try {
//                Runtime.getRuntime().exec("notepad.exe");
                new ProcessBuilder("open", "-a", "Notes").start();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }  else if(msg.equals("Calculator")) {
            try {
//                Runtime.getRuntime().exec("calc.exe");
                new ProcessBuilder("open", "-a", "Calculator").start();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }  else if(msg.equals("Exit")) {
            setVisible(false);
            new Login();
        }
    }
}
