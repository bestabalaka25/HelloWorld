import java.util.*;
public class L5E5 {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int score = 0;

        System.out.println("Welcome to the Math Quiz Game!");

        for (int i = 1; i <= 10; i++) {
            int num1 = random.nextInt(10); // Generate a random number between 0 and 9
            int num2 = random.nextInt(10);

            // Generate a random operator (+, -, or *)
            char operator;
            int operatorChoice = random.nextInt(3);
            switch (operatorChoice) {
                case 0:
                    operator = '+';
                    break;
                case 1:
                    operator = '-';
                    break;
                case 2:
                    operator = '*';
                    break;
                default:
                    operator = '+'; // Default to addition
                    break;
            }

            System.out.print("Question " + i + ": What is " + num1 + " " + operator + " " + num2 + "? ");
            int userAnswer = scanner.nextInt();

            int correctAnswer;
            switch (operator) {
                case '+':
                    correctAnswer = num1 + num2;
                    break;
                case '-':
                    correctAnswer = num1 - num2;
                    break;
                case '*':
                    correctAnswer = num1 * num2;
                    break;
                default:
                    correctAnswer = num1 + num2; // Default to addition
                    break;
            }

            if (userAnswer == correctAnswer) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + correctAnswer);
            }
        }

        System.out.println("Quiz completed! Your score is: " + score + " out of 10.");
        scanner.close();

    }
}
