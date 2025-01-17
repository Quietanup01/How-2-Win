package bank;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class RegisterCustomerPage extends JFrame implements ActionListener {
    JLabel nameLabel, addressLabel, mobileLabel, emailLabel, accountTypeLabel, balanceLabel, dobLabel, idProofLabel;
    JTextField nameField, addressField, mobileField, emailField, balanceField;
    JComboBox<String> accountTypeComboBox;
    JDateChooser dobChooser;
    JButton registerButton;

    public RegisterCustomerPage() {
        super("Register Customer");

        // Set background color for the frame
        getContentPane().setBackground(new Color(215, 230, 245));

        // Initialize components
        nameLabel = new JLabel("Full Name:");
        addressLabel = new JLabel("Address:");
        mobileLabel = new JLabel("Mobile No:");
        emailLabel = new JLabel("Email ID:");
        accountTypeLabel = new JLabel("Account Type:");
        balanceLabel = new JLabel("Initial Balance:");
        dobLabel = new JLabel("Date of Birth:");
        idProofLabel = new JLabel("ID Proof:");

        nameField = new JTextField(20);
        addressField = new JTextField(20);
        mobileField = new JTextField(20);
        emailField = new JTextField(20);
        balanceField = new JTextField(20);

        String[] accountTypes = {"Saving Account", "Current Account"};
        accountTypeComboBox = new JComboBox<>(accountTypes);

        dobChooser = new JDateChooser();

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 18)); // Set button text font size
        registerButton.addActionListener(this);

        // Set layout
        setLayout(new GridLayout(9, 2, 10, 5)); // Rows, Columns, Horizontal gap, Vertical gap

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(addressLabel);
        add(addressField);
        add(mobileLabel);
        add(mobileField);
        add(emailLabel);
        add(emailField);
        add(accountTypeLabel);
        add(accountTypeComboBox);
        add(balanceLabel);
        add(balanceField);
        add(dobLabel);
        add(dobChooser);
        add(idProofLabel);
        add(new JTextField(20)); // Placeholder for ID proof field
        add(new JLabel()); // Placeholder for alignment
        add(registerButton);

        // Set frame properties
        pack(); // Adjust frame size to fit components
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            try {
                // Generate card number and PIN
                String cardNumber = generateCardNumber();
                String pin = generatePIN();

                // Get user-entered data
                String fullName = nameField.getText();
                String address = addressField.getText();
                String mobileNo = mobileField.getText();
                String email = emailField.getText();
                String accountType = (String) accountTypeComboBox.getSelectedItem();
                int initialBalance = Integer.parseInt(balanceField.getText());
                java.sql.Date dob = new java.sql.Date(dobChooser.getDate().getTime());
                String idProof = ""; // You need to implement this part

                // Insert values into the customer table
                Connn c = new Connn();
                
                String query = "INSERT INTO customer (full_name, address, mobile_no, email_id, account_type, initial_balance, date_of_birth, id_proof, card_number, pin,balance) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = c.connection.prepareStatement(query);
                preparedStatement.setString(1, fullName);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, mobileNo);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, accountType);
                preparedStatement.setDouble(6, initialBalance);
                preparedStatement.setDate(7, dob);
                preparedStatement.setString(8, idProof);
                preparedStatement.setString(9, cardNumber);
                preparedStatement.setString(10, pin);
                preparedStatement.setInt(11, initialBalance);

                preparedStatement.executeUpdate();

                // Display success message
                JOptionPane.showMessageDialog(this, "Customer registered successfully!\nCard Number: " + cardNumber + "\nPIN: " + pin);
                new AdminHomePage();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while registering the customer. Please try again.");
            }
        }
    }

    // Generate a card number with less than 16 digits
    private String generateCardNumber() {
        Random random = new Random();
        long cardNumber = (long) (Math.pow(10, 15) * random.nextDouble());
        return String.format("%015d", cardNumber);
    }

    // Generate a PIN with less than 5 digits
    private String generatePIN() {
        Random random = new Random();
        int pin = random.nextInt(10000); // Generates a number between 0 and 9999
        return String.format("%04d", pin);
        // Format the PIN with leading zeros if necessary
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterCustomerPage::new);
    }
}
