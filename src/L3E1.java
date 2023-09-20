import java.util.Scanner;

public class L3E1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your Firstname?" + "\n");
        String name = input.nextLine();

        System.out.print("Enter your SurName?" + "\n");
        String secondName = input.nextLine();

        System.out.print("Enter your year of birth?" + "\n");
        String birth = input.nextLine();

        System.out.println("Username:" + name.substring(0,1) + secondName.toLowerCase());
        System.out.println("Password:" + secondName.toLowerCase().substring(0,1) + name.toUpperCase().substring(0,3) + birth);



    }
}
