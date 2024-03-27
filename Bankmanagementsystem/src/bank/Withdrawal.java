package bank;



import java.text.SimpleDateFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {

    String pin;
    
    TextField textField;

    JButton b1, b2;
    int balance;
    Withdrawal(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 700, 35);
        l3.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460, 220, 400, 35);
        l3.add(label2);

        textField = new TextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460, 260, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textField);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        
        setVisible(true);
    }
    
    static int getBalance(String pin) {
	    Connn c = new Connn();
	    String sql = "SELECT balance FROM customer WHERE pin = '" + pin + "'";

	    int balance = 0; // Initialize balance to handle cases where pin doesn't match any record

	    try {
	        ResultSet rs = c.statement.executeQuery(sql);
	        if (rs.next()) {
	            balance = rs.getInt("balance");
	        }
	        rs.close(); // Close the result set after retrieving data
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return balance;
	}

    @Override
    public void actionPerformed(ActionEvent e) {
    	 if (e.getSource() == b1) {
             try {
                 String amount = textField.getText();

                 Date date = new Date();
                 if (textField.getText().equals("")) {
                     JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdraw");
                 } else {
                     Connn c = new Connn();
                     int currentBalance = getBalance(pin); // Retrieve current balance from the database
                     int withdraw = Integer.parseInt(amount);

                     if (withdraw > currentBalance) {
                         JOptionPane.showMessageDialog(null, "Withdrawal amount exceeds current balance");
                     } else {
                         int finalBalance = currentBalance - withdraw; // Calculate final balance

                         // Construct the SQL query to insert the transaction into the bank table
                         String sql = "INSERT INTO bank (`pin`, `date`, `type`, `amount`, `final_balance`) " +
                                 "VALUES ('" + pin + "', '" + new SimpleDateFormat("yyyy-MM-dd").format(date) + "', 'Withdraw', " + withdraw + ", " + finalBalance + ")";
                         String sq2 = "UPDATE customer " +
                                 "SET balance = " + finalBalance + " " +
                                 "WHERE pin = '" + pin + "'";

                         c.statement.executeUpdate(sql); // Execute the SQL query
                         c.statement.executeUpdate(sq2);
                         JOptionPane.showMessageDialog(null, "Rs. " + amount + " Withdrawn Successfully");
                         setVisible(false);
                         new CustomerHomePage(pin);
        	        }
        	        setVisible(false);
        	        new CustomerHomePage(pin);
        	        // new main_Class(pin);
        	    }
        	} catch (Exception E) {
        	    E.printStackTrace();
        	}
        } else if (e.getSource() == b2) {
            setVisible(false);
            new CustomerHomePage (pin);
        }
    }

    public static void main(String[] args) {
        new Withdrawal("");
    }
}
