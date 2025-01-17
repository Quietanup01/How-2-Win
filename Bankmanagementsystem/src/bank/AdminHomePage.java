package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHomePage extends JFrame implements ActionListener {
    JButton registerButton;
    JButton deleteButton;
    JButton modifyButton;
    JButton viewButton;

    AdminHomePage() {
        super("Admin Home Page");

        // Set background color for the frame
        getContentPane().setBackground(new Color(215, 230, 245));

        // Create buttons with increased width and centered alignment
        registerButton = createButton("Register Customer", 220);
        deleteButton = createButton("Delete Customer", 220);
        modifyButton = createButton("Modify Customer", 220);
        viewButton = createButton("View Customer Details", 220);

        // Create panel to hold buttons with FlowLayout center alignment
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(215, 230, 245)); // Set background color for button panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(registerButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(viewButton);

        // Add buttons panel to the frame
        add(buttonPanel);

        // Set frame properties
        setSize(400, 500); // Increase the height of the frame to 500 pixels
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createButton(String text, int width) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setPreferredSize(new Dimension(width, 40));
        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
        	
        	
        	new RegisterCustomerPage();
        	 setVisible(false);
         //   JOptionPane.showMessageDialog(this, "Register Customer clicked");
        } else if (e.getSource() == deleteButton) {
            JOptionPane.showMessageDialog(this, "Delete Customer clicked");
        } else if (e.getSource() == modifyButton) {
            JOptionPane.showMessageDialog(this, "Modify Customer clicked");
        } else if (e.getSource() == viewButton) {
        	 new ViewCustomer();
            //JOptionPane.showMessageDialog(this, "View Customer Details clicked");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminHomePage());
    }
}
