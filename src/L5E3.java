import java.util.*;
public class L5E3 {
    public static void main(String [] args){
        Scanner table = new Scanner(System.in);

        System.out.print("Enter the base number for the times table: ");
        int base = table.nextInt();

        System.out.println("Times Table for " + base + ":");
        for (int i = 1; i <= 12; i++) {
            int result = base * i;
            System.out.println(base + " x " + i + " = " + result);
        }
    }
}
