import java.util.Scanner;

public class L3E3 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Cost of loan : £ ");
        double L = input.nextDouble();

        System.out.print("Enter the Interest rate: ");
        double j = (input.nextDouble() / 100) / 12;

        System.out.print("Enter The number of years for the loan: ");
        double n = input.nextDouble() * 12;

        double M = L * (j / (1 - Math.pow((1 + j),- n) ) );

        System.out.println("Loan Amount:" + L);
        System.out.format("Interest Rate (APR%%) per month: %.4f %n", j);
        System.out.println("Number of months:" + n);
        System.out.format("Monthly Repayment: %.2f %n", M);

    }
}
