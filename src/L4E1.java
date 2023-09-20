import java.util.*;

public class L4E1 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your Firstname?" + "\n");
        String name = input.nextLine();

        System.out.print("Enter your SurName?" + "\n");
        String secondName = input.nextLine();

        System.out.print("Enter your order total: £" + "\n");
        Integer order = Integer.valueOf(input.nextLine());

        System.out.print("Enter your amount you wish to pay as deposit: £" + "\n");
        Integer deposit = Integer.valueOf(input.nextLine());
        


        System.out.println("==RECEIPT==");
        System.out.println("Customer:"+ name.substring(0,1)  + secondName);
        System.out.print("Deposit paid: £" + deposit + "\n");
        System.out.format("How much left to pay: %.2f", (double)(order - deposit));
        if (deposit > 100) {
            System.out.println("You get a free toaster!");
        }
        if (deposit < 100){
            System.out.println("Have a nice day");
        }



    }
}
