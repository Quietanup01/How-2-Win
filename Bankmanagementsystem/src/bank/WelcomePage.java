package bank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class WelcomePage extends JFrame implements ActionListener {
    JButton adminButton;
    JButton customerButton;
    JPanel contentPane;

    WelcomePage() {
        super("Welcome Page");

        // Create buttons
        adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Arial", Font.PLAIN, 18));
        adminButton.addActionListener(this);

        customerButton = new JButton("Customer");
        customerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        customerButton.addActionListener(this);

        // Create panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(adminButton);
        buttonPanel.add(customerButton);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Load image and add it to the content pane
        contentPane = new ImagePanel();
        setContentPane(contentPane);

        // Add buttons panel to the content pane
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Pack and make the frame visible
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {
        	new Login();
        	setVisible(false);
            //JOptionPane.showMessageDialog(this, "Admin clicked");
        } else if (e.getSource() == customerButton) {
        	new CustomerLogin("");
        	setVisible(false);
           // JOptionPane.showMessageDialog(this, "Customer clicked");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomePage());
    }

    // Inner class for JPanel with background image
    private class ImagePanel extends JPanel {
        private BufferedImage backgroundImage;

        public ImagePanel() {
            try {
                URL imageUrl = getClass().getClassLoader().getResource("icon/backbg.png");
                backgroundImage = ImageIO.read(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                double scaleFactorX = getWidth() / (double) backgroundImage.getWidth();
                double scaleFactorY = getHeight() / (double) backgroundImage.getHeight();
                double scaleFactor = Math.max(scaleFactorX, scaleFactorY);
                int scaledWidth = (int) (backgroundImage.getWidth() * scaleFactor);
                int scaledHeight = (int) (backgroundImage.getHeight() * scaleFactor);
                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;
                g2d.drawImage(backgroundImage, x, y, scaledWidth, scaledHeight, null);
                g2d.dispose();
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(850, 480); // Set preferred size
        }
    }
}
