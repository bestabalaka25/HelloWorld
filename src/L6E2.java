import java.util.*;
public class L6E2 {
    public static void main(String [] args) {
        int option;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter an Option(1-4): ");
            option = scanner.nextInt();
            System.out.println("Option Selected: " + option);
            if (option == 1){
                System.out.println("Hello");
            } else if (option == 2) {
                //Something else
                System.out.println("Option 2");
            } else {
                System.out.println("Option 3");
            }
        } while(option != 4);
    }
}
