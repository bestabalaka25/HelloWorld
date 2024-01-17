import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;

public class CurrencyConverterApp extends JFrame {
    private Map<String, String> users = new HashMap<>();
    private Map<String, List<String>> conversionHistory = new HashMap<>();
    private boolean loggedIn = false;

    private int decimalPlaces = 2;  // Set the default number of decimal places

    private String defaultSourceCurrency = "";  // Set the default source currency

    private String currentUsername = null;
    private AbstractButton frame;
    private JComboBox<String> languageComboBox;

    private Map<String, String> userDefaultCurrencies = new HashMap<>();


    private JPanel cardPanel;
    private CardLayout cardLayout;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterApp app = new CurrencyConverterApp();
            app.runCurrencyConverterApp();
        });
    }

    public void runCurrencyConverterApp() {
        setTitle("BA Currency Converter App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel loginSignupPanel = createLoginSignupPanel();
        JPanel currencyConverterPanel = createCurrencyConverterPanel();

        cardPanel.add(loginSignupPanel, "LoginSignup");
        cardPanel.add(currencyConverterPanel, "CurrencyConverter");

        add(cardPanel);

        // Set the JFrame size
        setSize(4300, 7420); // Dividing by 2 for a reasonable size

        setLocationRelativeTo(null);
        setVisible(true);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JComboBox<String> defaultCurrencyComboBox;
    //Create a JPanel for the signup screen
    private JPanel createLoginSignupPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JSpinner decimalPlacesSpinner = new JSpinner(new SpinnerNumberModel(decimalPlaces, 0, 4, 1));
        decimalPlacesSpinner.addChangeListener(e -> decimalPlaces = (int) decimalPlacesSpinner.getValue());

        String[] currencyCodes = {"USD", "EUR", "GBP", "CAD", "JPY", "AUD", "CHF", "CNY", "INR", "MXN"};

        defaultCurrencyComboBox = new JComboBox<>(currencyCodes);
        defaultCurrencyComboBox.setSelectedItem(defaultSourceCurrency);
        defaultCurrencyComboBox.addActionListener(e -> defaultSourceCurrency = (String) defaultCurrencyComboBox.getSelectedItem());

        JButton signupButton = createStyledButton("Sign Up");
        JButton loginButton = createStyledButton("Log In");

        JComboBox<String> defaultCurrencyComboBox = new JComboBox<>(currencyCodes);
        defaultCurrencyComboBox.setSelectedItem(defaultSourceCurrency);  // Set the default source currency

        signupButton.addActionListener(e -> {
            if (validateInput(usernameField.getText(), passwordField.getPassword())) {
                if (signUp(usernameField.getText(), new String(passwordField.getPassword()), defaultSourceCurrency)) {
                    usernameField.setText("");
                    passwordField.setText("");
                    switchToCurrencyConverterScreen();
                }
            } else {
                showMessageDialog(this, "Please enter a valid username and password.");
            }
        });


        loginButton.addActionListener(e -> {
            currentUsername = logIn(usernameField.getText(), new String(passwordField.getPassword()));
            if (currentUsername != null) {
                loggedIn = true;
                defaultSourceCurrency = userDefaultCurrencies.get(currentUsername);  // Set the default currency
                switchToCurrencyConverterScreen();
                showMessageDialog(this, "Log in successful.");
            } else {
                showMessageDialog(this, "Log in failed.");
            }
        });

        signupButton.setForeground(Color.WHITE);
        loginButton.setForeground(Color.WHITE);

        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        panel.add(new JLabel("Decimal Places: "));
        panel.add(decimalPlacesSpinner);
        panel.add(new JLabel("Default Source Currency: "));
        panel.add(defaultCurrencyComboBox);
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
        fromCurrencyComboBox.setSelectedItem(defaultSourceCurrency);  // Set the default source currency

        JComboBox<String> toCurrencyComboBox = new JComboBox<>(currencyCodes);


        JButton convertButton = createStyledButton("Convert");
        JButton conversionHistoryButton = createStyledButton("Conversion History");
        JButton helpButton = createStyledButton("Help");
        JButton logoutButton = createStyledButton("Log Out");

        convertButton.addActionListener(e -> {
            convertCurrency(
                    Double.parseDouble(amountField.getText()),
                    (String) fromCurrencyComboBox.getSelectedItem(),
                    (String) toCurrencyComboBox.getSelectedItem()
            );
            amountField.setText("");
        });

        conversionHistoryButton.addActionListener(e -> showConversionHistory());

        helpButton.addActionListener(e -> showContactUsForm());

        logoutButton.addActionListener(e -> {
            loggedIn = false;
            currentUsername = null;
            cardLayout.show(cardPanel, "LoginSignup");
            showMessageDialog(this, "Log out successful.");
        });



        panel.add(new JLabel("Amount: "));
        panel.add(amountField);
        panel.add(new JLabel("From Currency: "));
        panel.add(fromCurrencyComboBox);
        panel.add(new JLabel("To Currency: "));
        panel.add(toCurrencyComboBox);
        panel.add(convertButton);
        panel.add(conversionHistoryButton);
        panel.add(helpButton);
        panel.add(logoutButton);

        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#FF0029"));
        button.setForeground(Color.WHITE);
        return button;
    }

    private boolean validateInput(String username, char[] password) {
        return !username.isEmpty() && password.length > 0;
    }

    private boolean signUp(String username, String password, String defaultCurrency) {
        if (users.containsKey(username)) {
            showMessageDialog(this, "Username already exists. Please choose a different username.");
            return false;
        } else {
            users.put(username, password);
            conversionHistory.put(username, new ArrayList<>());
            // Store the default currency along with the user
            // (You may need to modify your data structure accordingly)
            userDefaultCurrencies.put(username, defaultCurrency);
            showMessageDialog(this, "Sign up successful!");
            return true;
        }
    }


    private String logIn(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            return username;
        }
        return null;
    }


    private void switchToCurrencyConverterScreen() {
        cardLayout.show(cardPanel, "CurrencyConverter");
    }

    private void convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double conversionRate = getConversionRate(fromCurrency, toCurrency);

        if (conversionRate > 0) {
            double result = amount * conversionRate;

            // Get the user's preferred decimal places
            int decimalPlaces = this.decimalPlaces;

            // Format the result with the specified number of decimal places
            String formattedResult = String.format("%." + decimalPlaces + "f %s is equal to %." + decimalPlaces + "f %s",
                    amount, fromCurrency, result, toCurrency);

            showMessageDialog(this, formattedResult);
            addToConversionHistory(currentUsername, formattedResult);
        } else {
            showMessageDialog(this, "Invalid currency pair. Please enter valid currency codes (USD, EUR, GBP, CAD, JPY, AUD, CHF, CNY, INR, MXN).");
        }
    }


    private void showConversionHistory() {
        List<String> history = conversionHistory.getOrDefault(currentUsername, new ArrayList<>());
        StringBuilder historyMessage = new StringBuilder("Conversion History:\n");
        for (String entry : history) {
            historyMessage.append(entry).append("\n");
        }
        showMessageDialog(this, historyMessage.toString());
    }

    private void addToConversionHistory(String username, String entry) {
        List<String> history = conversionHistory.getOrDefault(username, new ArrayList<>());
        history.add(entry);
        conversionHistory.put(username, history);
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
        // Updated conversion rates to include CAD, JPY, AUD, CHF, CNY, INR, MXN
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
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("EUR")) {
            return 0.69;
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("GBP")) {
            return 0.73;
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("INR")) {
            return 60.44;
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("MXN")) {
            return 20.79;
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("AUD")) {
            return 9.62;
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("CHF")) {
            return 15.94;
        } else if (fromCurrency.equals("JPY") && toCurrency.equals("CAD")) {
            return 190.51;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("USD")) {
            return 0.75;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("EUR")) {
            return 0.90;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("GBP")) {
            return 0.79;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("INR")) {
            return 90.44;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("MXN")) {
            return 60.79;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("JPY")) {
            return 10.62;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("CHF")) {
            return 19.94;
        } else if (fromCurrency.equals("AUD") && toCurrency.equals("CAD")) {
            return 130.51;
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("USD")) {
            return 1.09;
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("EUR")) {
            return 1.90;
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("GBP")) {
            return 2.79;
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("INR")) {
            return 0.74;
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("MXN")) {
            return 80.79;
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("JPY")) {
            return 56.62;
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("CHF")) {
            return 28.94;
        } else if (fromCurrency.equals("CHF") && toCurrency.equals("CAD")) {
            return 160.51;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("USD")) {
            return 0.16;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("EUR")) {
            return 1.90;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("GBP")) {
            return 5.79;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("INR")) {
            return 6.74;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("MXN")) {
            return 70.79;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("JPY")) {
            return 59.62;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("AUD")) {
            return 30.94;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("CHF")) {
            return 40.54;
        } else if (fromCurrency.equals("CNY") && toCurrency.equals("CAD")) {
            return 170.51;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("EUR")) {
            return 1.90;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("GBP")) {
            return 9.79;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("CNY")) {
            return 2.74;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("MXN")) {
            return 50.79;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("JPY")) {
            return 99.62;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("AUD")) {
            return 70.94;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("CHF")) {
            return 60.54;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("CAD")) {
            return 200.51;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("USD")) {
            return 0.013;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("USD")) {
            return 0.049;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("EUR")) {
            return 3.90;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("GBP")) {
            return 20.79;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("CNY")) {
            return 45.74;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("INR")) {
            return 60.79;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("JPY")) {
            return 100.62;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("AUD")) {
            return 90.94;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("CHF")) {
            return 80.54;
        } else if (fromCurrency.equals("MXN") && toCurrency.equals("CAD")) {
            return 210.51;
        } else {
            return -1.0; // Invalid currency pair
        }
    }

    private void showContactUsForm() {
        JFrame contactUsFrame = new JFrame("Contact Us");
        contactUsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contactUsFrame.setSize(400, 300);
        contactUsFrame.setLayout(new FlowLayout());

        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField subjectField = new JTextField(20);
        JTextArea messageArea = new JTextArea(10, 30);

        JButton submitButton = createStyledButton("Submit");

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String subject = subjectField.getText();
            String message = messageArea.getText();

            if (name.isEmpty() || email.isEmpty() || subject.isEmpty() || message.isEmpty()) {
                showMessageDialog(contactUsFrame, "Please fill out all fields.");
            } else {
                // Contact form message
                showMessageDialog(contactUsFrame, "Thank you for reaching out. We will get back to you soon!");

                // Optionally,clear the form fields
                nameField.setText("");
                emailField.setText("");
                subjectField.setText("");
                messageArea.setText("");
            }
        });

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name: "));
        namePanel.add(nameField);

        JPanel emailPanel = new JPanel();
        emailPanel.add(new JLabel("Email: "));
        emailPanel.add(emailField);

        JPanel subjectPanel = new JPanel();
        subjectPanel.add(new JLabel("Subject: "));
        subjectPanel.add(subjectField);

        contactUsFrame.add(namePanel);
        contactUsFrame.add(emailPanel);
        contactUsFrame.add(subjectPanel);
        contactUsFrame.add(new JLabel("Message: "));
        contactUsFrame.add(new JScrollPane(messageArea));
        contactUsFrame.add(submitButton);

        contactUsFrame.setLocationRelativeTo(null);
        contactUsFrame.setVisible(true);
    }
}