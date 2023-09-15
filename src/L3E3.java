import java.util.Scanner;

public class L3E3 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Cost of loan : £ ");
        String L = input.nextLine();

        System.out.print("Enter the Interest rate: ");
        String j = input.nextLine();

        System.out.print("Enter The number of years for the loan: ");
        String n = input.nextLine();

        Integer M = L(j / (1 + "-" +(1 + j)^ + "-" + n + "*" + 12));

        System.out.println("Loan Amount:" + L);
        System.out.println("Interest Rate (APR%):" + j);
        System.out.println("Number of years:" + n);
        System.out.println("Monthly Repayment:" + M);

    }
}
