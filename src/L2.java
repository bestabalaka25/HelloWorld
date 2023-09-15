import java.util.*;

public class L2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name?"  + "\n");
        String name = input.nextLine();

        System.out.print("What is your Favourite movie?"  + "\n");
        String movie = input.nextLine();

        System.out.print("What is your city?" + "\n ");
        String city = input.nextLine();

        System.out.println("Hello my name is " + name +","  + "My favourite movie is " + movie +"," + "I live in " +  city +"," + "Nice to meet you"  +"\n");


    }
}
