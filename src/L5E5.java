import java.util.*;
public class L5E5 {
    public static void main(String [] args) {
            Scanner scanner = new Scanner(System.in);
            int score = 0;
            for (int questionNumber = 1; questionNumber <= 10; questionNumber++) {
                int num1 = (int) (Math.random() * 10);
                int num2 = (int) (Math.random() * 10);
                int correctAnswer = num1 + num2;

                System.out.print("Question " + questionNumber + ": What is " + num1 + " + " + num2 + "? ");
                int userAnswer = scanner.nextInt();

                if (userAnswer == correctAnswer) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer is " + correctAnswer + ".");
                }
            }

            System.out.println("Quiz completed!");
            System.out.println("Your score: " + score + " out of 10.");

            // Close the scanner
            scanner.close();
        }
    }
