package bank;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class DeleteCustomer extends JFrame implements ActionListener {
    
    Choice cCustomerId;
    JButton delete, back;
    
    DeleteCustomer() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel labelCustomerId = new JLabel("Customer ID");
        labelCustomerId.setBounds(50, 50, 100, 30);
        add(labelCustomerId);
        
        cCustomerId = new Choice();
        cCustomerId.setBounds(200, 50, 150, 30);
        add(cCustomerId);
        
        try {
            Connn c = new Connn();
            String query = "select * from customer";
            ResultSet rs = c.statement.executeQuery(query);
            while(rs.next()) {
                cCustomerId.add(rs.getString("customer_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);
        
        back = new JButton("Back");
        back.setBounds(220, 300, 100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        setSize(400, 400);
        setLocation(300, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Connn c = new Connn();
                String query = "delete from customer where customer_id = '"+cCustomerId.getSelectedItem()+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer Information Deleted Successfully");
                setVisible(false);
                new AdminHomePage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new AdminHomePage();
        }
    }

    public static void main(String[] args) {
        new DeleteCustomer();
    }
}
