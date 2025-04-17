import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

// ------------------------ Login Page ------------------------

public class LoginPage extends JFrame implements ActionListener {

    JLabel userLabel, passLabel;
    JTextField userText;
    JPasswordField passText;
    JButton loginButton;
    BufferedImage backgroundImage;

    public LoginPage() {
        setTitle("Login Page");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load background image
        try {
            File imageFile = new File("downloads/1.jpeg");
            if (imageFile.exists()) {
                backgroundImage = ImageIO.read(imageFile);
            } else {
                System.err.println("Background image not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Custom background panel
        JPanel contentPane = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Username
        userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 150, 80, 25);
        contentPane.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(150, 150, 200, 25);
        contentPane.add(userText);

        // Password
        passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 190, 80, 25);
        contentPane.add(passLabel);

        passText = new JPasswordField(20);
        passText.setBounds(150, 190, 200, 25);
        contentPane.add(passText);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(175, 240, 100, 30);
        loginButton.addActionListener(this);
        contentPane.add(loginButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userText.getText();
            String password = new String(passText.getPassword());

            if ("admin".equals(username) && "password123".equals(password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                SwingUtilities.invokeLater(() -> new OptionsPage());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}

// ------------------------ Options Page ------------------------

class OptionsPage extends JFrame implements ActionListener {

    JComboBox<String> pickupLocation;
    JComboBox<String> dropLocation;
    JButton submitButton;
    BufferedImage backgroundImage;

    String[] locations = {
        "Triveni Ghat", "Ram Jhula", "Lakshman Jhula",
        "Main Market", "City Hospital", "Railway Station"
    };

    public OptionsPage() {
        setTitle("Pickup and Drop Location");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load background image
        try {
            File imageFile = new File("downloads/2.jpg");
            if (imageFile.exists()) {
                backgroundImage = ImageIO.read(imageFile);
            } else {
                System.err.println("Background image not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Custom background panel
        JPanel contentPane = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Pickup Label and ComboBox
        JLabel pickupLabel = new JLabel("Pickup Location:");
        pickupLabel.setForeground(Color.WHITE);
        pickupLabel.setBounds(50, 60, 120, 25);
        contentPane.add(pickupLabel);

        pickupLocation = new JComboBox<>(locations);
        pickupLocation.setBounds(200, 60, 200, 25);
        contentPane.add(pickupLocation);

        // Drop Label and ComboBox
        JLabel dropLabel = new JLabel("Drop Location:");
        dropLabel.setForeground(Color.WHITE);
        dropLabel.setBounds(50, 110, 120, 25);
        contentPane.add(dropLabel);

        dropLocation = new JComboBox<>(locations);
        dropLocation.setBounds(200, 110, 200, 25);
        contentPane.add(dropLocation);

        // Submit Button
        submitButton = new JButton("Book Ride");
        submitButton.setBounds(180, 180, 120, 30);
        submitButton.addActionListener(this);
        contentPane.add(submitButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String pickup = (String) pickupLocation.getSelectedItem();
            String drop = (String) dropLocation.getSelectedItem();

            if (pickup != null && drop != null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Ride Details:\nPickup: " + pickup + "\nDrop: " + drop + "\nFare: ₹50",
                    "Booking Confirmation",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Please select both pickup and drop locations.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
