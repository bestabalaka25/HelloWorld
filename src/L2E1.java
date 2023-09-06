import java.util.*;

public class L2E1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name?" + "\n");
        String name = input.nextLine();

        System.out.print("What is your hobby?" + "\n");
        String hobby = input.nextLine();

        System.out.println(name  + "loves" +  hobby +  "in their free time");
        String lineBreak = "\n";
        System.out.println("They find it relaxing and satisfying when they are outdoor in nature"  + hobby +"." + hobby + "makes them feel happy about life");
    }

}