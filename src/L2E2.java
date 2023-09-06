import java.util.*;
public class L2E2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        double num1 = input.nextDouble();

        System.out.print("Enter the second number: ");
        double num2 = input.nextDouble();

        float divResult = (float) num1 / (float) num2;

        double addition = num1 + num2;
        System.out.println(num1 + "+" + num2 + "=" + addition);

        double subtraction = num1 - num2;
        System.out.println(num1 + "-" + num2 + "=" + subtraction);

        double multiplication = num1 * num2;
        System.out.println(num1 + "*" + num2 + "=" + multiplication);

        double division = num1 / num2;
        System.out.println(num1 + "/" + num2 + "=" + division);

        double modulus = num1 % num2;
        System.out.println(num1 + "%" + num2 + "=" + modulus);
    }
}
