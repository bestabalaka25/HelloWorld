import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterApp extends JFrame {
    private Map<String, String> users = new HashMap<>();
    private boolean loggedIn = false;
    private String currentUsername = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterApp app = new CurrencyConverterApp();
            app.runCurrencyConverterApp();
        });
    }

    public void runCurrencyConverterApp() {
        setTitle("BA Currency Converter App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login/Signup", createLoginSignupPanel());
        tabbedPane.addTab("Currency Converter", createCurrencyConverterPanel());

        add(tabbedPane);
        // Set the JFrame size to match the iPhone 12 dimensions
        setSize(2532 / 2, 1170 / 2); // Dividing by 2 for a reasonable size

        setLocationRelativeTo(null);
        setVisible(true);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createLoginSignupPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton signupButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Log In");

        signupButton.addActionListener(e -> {
            signUp(usernameField.getText(), new String(passwordField.getPassword()));
            usernameField.setText("");
            passwordField.setText("");
        });

        loginButton.addActionListener(e -> {
            currentUsername = logIn(usernameField.getText(), new String(passwordField.getPassword()));
            if (currentUsername != null) {
                loggedIn = true;
                switchToCurrencyConverter();
                JOptionPane.showMessageDialog(this, "Log in successful.");
            } else {
                JOptionPane.showMessageDialog(this, "Log in failed.");
            }
        });

        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        panel.add(signupButton);
        panel.add(loginButton);

        return panel;
    }

    private JPanel createCurrencyConverterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JTextField amountField = new JTextField(10);

        // Create JComboBox for currency selection
        String[] currencyCodes = {"USD", "EUR", "GBP", "CAD", "JPY", "AUD", "CHF", "CNY", "INR", "MXN"};
        JComboBox<String> fromCurrencyComboBox = new JComboBox<>(currencyCodes);
        JComboBox<String> toCurrencyComboBox = new JComboBox<>(currencyCodes);

        JButton convertButton = new JButton("Convert");


        convertButton.addActionListener(e -> {
            convertCurrency(
                    Double.parseDouble(amountField.getText()),
                    (String) fromCurrencyComboBox.getSelectedItem(),
                    (String) toCurrencyComboBox.getSelectedItem()
            );
            amountField.setText("");
        });

        panel.add(new JLabel("Amount: "));
        panel.add(amountField);
        panel.add(new JLabel("From Currency: "));
        panel.add(fromCurrencyComboBox);
        panel.add(new JLabel("To Currency: "));
        panel.add(toCurrencyComboBox);
        panel.add(convertButton);

        return panel;
    }

    private void signUp(String username, String password) {
        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.");
        } else {
            users.put(username, password);
            JOptionPane.showMessageDialog(this, "Sign up successful!");
        }
    }

    private String logIn(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            return username;
        }
        return null;
    }

    private void convertCurrency(double amount, String fromCurrency, String toCurrency) {
        if (!isValidCurrency(fromCurrency) || !isValidCurrency(toCurrency)) {
            JOptionPane.showMessageDialog(this, "Invalid source or target currency. Please enter valid currency codes (USD, EUR, GBP, CAD, JPY, AUD, CHF, CNY, INR, MXN).");
            return;
        }

        double conversionRate = getConversionRate(fromCurrency, toCurrency);

        if (conversionRate > 0) {
            double result = amount * conversionRate;
            JOptionPane.showMessageDialog(this, String.format("%.2f %s is equal to %.2f %s", amount, fromCurrency, result, toCurrency));
        } else {
            JOptionPane.showMessageDialog(this, "Invalid currency pair. Please enter valid currency codes (USD, EUR, GBP, CAD, JPY, AUD, CHF, CNY, INR, MXN).");
        }
    }

    private boolean isValidCurrency(String currency) {
        String[] validCurrencies = {"USD", "EUR", "GBP", "CAD", "JPY", "AUD", "CHF", "CNY", "INR", "MXN"};
        for (String validCurrency : validCurrencies) {
            if (validCurrency.equals(currency)) {
                return true;
            }
        }
        return false;
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
        // Update the conversion rates to include CAD, JPY, AUD, CHF, CNY, INR, MXN
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return 0.88;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) {
            return 0.78;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("CAD")) {
            return 1.27;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("JPY")) {
            return 110.20;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("AUD")) {
            return 1.34;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("CHF")) {
            return 0.92;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("CNY")) {
            return 6.37;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("INR")) {
            return 75.48;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("MXN")) {
            return 20.26;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            return 1.14;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("GBP")) {
            return 0.89;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("CAD")) {
            return 1.44;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("INR")) {
            return 92.44;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("MXN")) {
            return 18.79;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("AUD")) {
            return 1.62;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("CHF")) {
            return 4.94;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("JPY")) {
            return 157.51;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("CNY")) {
            return 17.51;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) {
            return 1.29;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("EUR")) {
            return 1.12;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("CAD")) {
            return 1.58;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("INR")) {
            return 70.44;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("MXN")) {
            return 20.79;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("AUD")) {
            return 5.62;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("CHF")) {
            return 8.94;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("JPY")) {
            return 137.51;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("CNY")) {
            return 27.51;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("USD")) {
            return 0.79;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("EUR")) {
            return 0.69;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("GBP")) {
            return 0.63;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("INR")) {
            return 90.44;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("MXN")) {
            return 30.79;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("AUD")) {
            return 7.62;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("CHF")) {
            return 10.94;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("JPY")) {
            return 187.51;
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("USD")) {
            return 0.0091;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("USD")) {
            return 0.75;
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("USD")) {
            return 1.09;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("USD")) {
            return 0.16;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("USD")) {
            return 0.013;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("USD")) {
            return 0.049;
        } else {
            return -1.0; // Invalid currency pair
        }
    }

    private void switchToCurrencyConverter() {
        JTabbedPane tabbedPane = (JTabbedPane) getContentPane().getComponent(0);
        tabbedPane.setSelectedIndex(1); // Switch to the Currency Converter tab
    }
}
