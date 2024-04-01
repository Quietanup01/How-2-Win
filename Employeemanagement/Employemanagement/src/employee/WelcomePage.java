package employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame implements ActionListener {
   
    JButton adminLogin, employeeLogin;
    
    WelcomePage() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(150, 30, 1000, 40); // Adjusted bounds for the heading label
        heading.setFont(new Font("serif", Font.PLAIN, 40)); // Reduced font size
        heading.setForeground(Color.RED);
        add(heading);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/backbg.png"));
        Image i2 = i1.getImage().getScaledInstance(1100, 600, Image.SCALE_DEFAULT); // Adjusted image size
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 100, 1050, 500); // Adjusted image bounds
        add(image);
        
        adminLogin = new JButton("Admin Login");
        adminLogin.setBounds(400, 300, 300, 70);
        adminLogin.setBackground(Color.BLACK);
        adminLogin.setForeground(Color.WHITE);
        adminLogin.addActionListener(this);
        image.add(adminLogin);

        employeeLogin = new JButton("Employee Login");
        employeeLogin.setBounds(710, 300, 300, 70);
        employeeLogin.setBackground(Color.BLACK);
        employeeLogin.setForeground(Color.WHITE);
        employeeLogin.addActionListener(this);
        image.add(employeeLogin);

        setSize(1150, 600); // Reduced frame size
        setLocationRelativeTo(null); // Centered the frame
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == adminLogin) {
            setVisible(false);
            new Login();
        } else {
            setVisible(false);
            new EmployeeLogin();
        }
    }
    
    public static void main(String args[]) {
        new WelcomePage();
    }
}
