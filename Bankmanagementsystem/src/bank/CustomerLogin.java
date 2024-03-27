package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerLogin extends JFrame implements ActionListener {
    JLabel label2, label3;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button3;
	String pin;
	int balance;

    CustomerLogin(String pin) {
        super("Customer Login");

        label2 = new JLabel("Card Number:");
        label2.setFont(new Font("Ralway", Font.BOLD, 20));
        label2.setForeground(Color.WHITE);
        label2.setBounds(150, 190, 375, 30);
        add(label2);

        textField2 = new JTextField(15);
        textField2.setBounds(325, 190, 230, 30);
        textField2.setFont(new Font("Arial", Font.BOLD, 14));
        add(textField2);

        label3 = new JLabel("PIN:");
        label3.setFont(new Font("Ralway", Font.BOLD, 20));
        label3.setForeground(Color.WHITE);
        label3.setBounds(150, 250, 375, 30);
        add(label3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(325, 250, 230, 30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField3);
        this.pin = new String(passwordField3.getPassword());

        button3 = new JButton("Login");
        button3.setFont(new Font("Arial", Font.BOLD, 14));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setBounds(300, 320, 230, 30);
        button3.addActionListener(this);
        add(button3);

        ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image iii2 = iii1.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel iiimage = new JLabel(iii3);
        iiimage.setBounds(0, 0, 850, 480);
        add(iiimage);

        getContentPane().setBackground(Color.DARK_GRAY);

        setLayout(null);
        setSize(850, 480);
        setLocation(350, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == button3) {
                // Add your login logic here
                String cardNo = textField2.getText();
                String pin = new String(passwordField3.getPassword());
                
                Connn c = new Connn();
                String q = "select * from customer where card_number = '"+cardNo+"' and  pin = '"+pin+"'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()){
                    setVisible(false);
					new CustomerHomePage(pin);
                  //  new Login();
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Id and Password");
                }
                // You can replace the print statements with your actual login logic
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CustomerLogin("");
    }
}

