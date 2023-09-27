import java.util.*;
public class L5E3 {
    public static void main(String [] args){
        Scanner table = new Scanner(System.in);

        System.out.print("Enter the base number for the times table: ");
        int base = table.nextInt();

        System.out.println("Times Table for " + base + ":");
        for (int num = 1; num <= 12; num++) {
            int result = base * num;
            System.out.println(base + " x " + num + " = " + result);
        }
    }
}
