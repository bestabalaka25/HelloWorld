import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CurrencyConverterApp {
    private Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        CurrencyConverterApp app = new CurrencyConverterApp();
        app.runCurrencyConverterApp();
    }

    public void runCurrencyConverterApp() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        String currentUsername = null;

        while (true) {
            System.out.println("Currency Converter App");
            if (!loggedIn) {
                System.out.println("1. Sign Up");
                System.out.println("2. Log In");
                System.out.println("3. Exit");
            } else {
                System.out.println("1. Convert Currency");
                System.out.println("2. Log Out");
            }

            System.out.print("Select an option: ");
            String option = scanner.nextLine();

            if (!loggedIn) {
                switch (option) {
                    case "1":
                        signUp(scanner);
                        break;
                    case "2":
                        currentUsername = logIn(scanner);
                        if (currentUsername != null) {
                            loggedIn = true;
                            System.out.println("Log in successful.");
                        } else {
                            System.out.println("Log in failed.");
                        }
                        break;
                    case "3":
                        System.out.println("Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } else {
                switch (option) {
                    case "1":
                        convertCurrency(scanner);
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

    private void signUp(Scanner scanner) {
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

    private String logIn(Scanner scanner) {
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

    private void convertCurrency(Scanner scanner) {
        System.out.println("Currency Conversion");
        System.out.print("Enter the amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.print("From currency (USD, EUR, GBP, CAD): ");
        String fromCurrency = scanner.nextLine().toUpperCase();

        System.out.print("To currency (USD, EUR, GBP, CAD): ");
        String toCurrency = scanner.nextLine().toUpperCase();

        double conversionRate = getConversionRate(fromCurrency, toCurrency);

        if (conversionRate > 0) {
            double result = amount * conversionRate;
            System.out.printf("%.2f %s is equal to %.2f %s\n", amount, fromCurrency, result, toCurrency);
        } else {
            System.out.println("Invalid currency selection.");
        }
    }

    private double getConversionRate(String fromCurrency, String toCurrency) {
        // You can implement a real exchange rate service here or use predefined rates.
        // In this example, we use a simple hardcoded conversion rate.
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
