package bank;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.*;

import java.sql.*;

import java.awt.event.*;

public class Transaction extends JFrame implements ActionListener {

    JTable table;
    Choice cpin;
    JButton print, back;
    String pin;

    Transaction(String pin) {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

//        JLabel searchlbl = new JLabel("Search by PIN");
//        searchlbl.setBounds(20, 20, 150, 20);
//        add(searchlbl);
//
//        cpin = new Choice();
//        cpin.setBounds(180, 20, 150, 20);
//        add(cpin);
//
//        try {
//            Connn c = new Connn();
//            ResultSet rs = c.statement.executeQuery("SELECT pin FROM bank");
//            while (rs.next()) {
//                cpin.add(rs.getString("pin"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        table = new JTable();

        try {
            Connn c = new Connn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");

            //ResultSet rs = c.statement.executeQuery("SELECT * FROM bank ");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        back = new JButton("Back");
        back.setBounds(220, 70, 80, 20);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            //new CustomerHomePage(pin);
        }
    }

    public static void main(String[] args) {
        //new Transaction();
    }
}
