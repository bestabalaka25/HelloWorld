import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverterApp {

    private Map<String, String> users = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean loggedIn = false;
    private String currentUsername = null;

    public static void main(String[] args) {
        currencyapp app = new currencyapp();
        app.runCurrencyConverterApp();
    }

    public void runCurrencyConverterApp() {
        while (true) {
            printMenu();
            String option = scanner.nextLine();

            if (!loggedIn) {
                switch (option) {
                    case "1":
                        signUp();
                        break;
                    case "2":
                        currentUsername = logIn();
                        if (currentUsername != null) {
                            loggedIn = true;
                            System.out.println("Log in successful.");
                        } else {
                            System.out.println("Log in failed.");
                        }
                        break;
                    case "3":
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } else {
                switch (option) {
                    case "1":
                        convertCurrency();
                        break;
                    case "2":
                        loggedIn = false;
                        System.out.println("Log out successful.");
                        currentUsername = null;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("Welcome to BA Currency Converter App");
        if (!loggedIn) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
        } else {
            System.out.println("1. Convert Currency");
            System.out.println("2. Log Out");
        }
        System.out.print("Select an option: ");
    }

    private void signUp() {
        System.out.println("Sign Up");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        users.put(username, password);
        System.out.println("Sign up successful!");
    }

    private String logIn() {
        System.out.println("Log In");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            return username;
        }

        return null;
    }

    private void convertCurrency() {
        System.out.println("Currency Conversion");
        System.out.print("Enter the amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.print("From currency (USD, EUR, GBP, CAD): ");
        String fromCurrency = scanner.nextLine().toUpperCase();

        System.out.print("To currency (USD, EUR, GBP, CAD): ");
        String toCurrency = scanner.nextLine().toUpperCase();

        if (!isValidCurrency(fromCurrency) || !isValidCurrency(toCurrency)) {
            System.out.println("Invalid source or target currency. Please enter valid currency codes (USD, EUR, GBP, CAD).");
            return;
        }

        double conversionRate = getConversionRate(fromCurrency, toCurrency);

        if (conversionRate > 0) {
            double result = amount * conversionRate;
            System.out.printf("%.2f %s is equal to %.2f %s\n", amount, fromCurrency, result, toCurrency);
        } else {
            System.out.println("Invalid currency selection. Please enter valid currency codes (USD, EUR, GBP, CAD).");
        }
    }

    private boolean isValidCurrency(String currency) {
        // List of valid currency codes
        String[] validCurrencies = {"USD", "EUR", "GBP", "CAD"};
        return Arrays.asList(validCurrencies).contains(currency);
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
        // Update the conversion rates to include CAD
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return 0.88;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) {
            return 0.78;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("CAD")) {
            return 1.27;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            return 1.14;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("GBP")) {
            return 0.89;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("CAD")) {
            return 1.44;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) {
            return 1.29;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("EUR")) {
            return 1.12;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("CAD")) {
            return 1.58;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("USD")) {
            return 0.79;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("EUR")) {
            return 0.69;
        } else if (fromCurrency.equals("CAD") && toCurrency.equals("GBP")) {
            return 0.63;
        } else {
            return -1.0; // Invalid currency pair
        }
    }
}
