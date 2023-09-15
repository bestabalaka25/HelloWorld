import java.util.Scanner;

public class L3E2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("What is the capital of Spain?" + "\n");
        String message = "Madrid";
        System.out.println(message.contains("Madrid")); // true
        System.out.println(message.contains("madrid")); // false
        String quiz1 = input.nextLine();

        System.out.print("What is the capital of France?" + "\n");
        String france = "Paris";
        System.out.println(message.contains("Paris, paris")); // true
        System.out.println(message.contains("")); // false
        String quiz2 = input.nextLine();

        System.out.print("What is the capital of Italy?" + "\n");
        String italy = "Hello World";
        System.out.println(message.contains("Rome, rome")); // true
        System.out.println(message.contains("")); // false
        String quiz3 = input.nextLine();

    }
}
