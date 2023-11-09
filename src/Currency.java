import java.util.*;
public class Currency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Currency Converter App");
        while (true) {
            System.out.print("Enter the amount to convert (or type 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                double amount = Double.parseDouble(input);

                System.out.print("Enter the source currency: ");
                String sourceCurrency = scanner.nextLine().toUpperCase();

                System.out.print("Enter the target currency: ");
                String targetCurrency = scanner.nextLine().toUpperCase();

                double convertedAmount = convertCurrency(amount, sourceCurrency, targetCurrency);

                System.out.println(amount + " " + sourceCurrency + " is equal to " + convertedAmount + " " + targetCurrency);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }


        scanner.close();
    }


    public static double convertCurrency(double amount, String sourceCurrency, String targetCurrency) {
        // In a real application, you would fetch exchange rates from an API.
        // For simplicity, we'll use a fixed conversion rate here.
        double conversionRate = 1.2; // 1 USD to EUR for example
        return amount * conversionRate;
    }
}
