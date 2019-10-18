import java.security.SecureRandom;
import java.util.Scanner;

public class morton_p1 {

    // creates new question based on chosen difficulty and question type
    public static double newQuestion(SecureRandom rand, int difficulty, int arithmetic) {
        int rand1, rand2;

        if (difficulty == 1) {
            rand1 = getRandomInt(rand, 10) + 1;
            rand2 = getRandomInt(rand, 10) + 1;
        } else if (difficulty == 2) {
            rand1 = getRandomInt(rand, 100) + 1;
            rand2 = getRandomInt(rand, 100) + 1;
        } else if (difficulty == 3) {
            rand1 = getRandomInt(rand, 1000) + 1;
            rand2 = getRandomInt(rand, 1000) + 1;
        } else if (difficulty == 4) {
            rand1 = getRandomInt(rand, 10000) + 1;
            rand2 = getRandomInt(rand, 10000) + 1;
        } else {
            System.out.println("Invalid Input");
            rand1 = -999999;
            rand2 = -999999;
        }

        return arithmeticOperation(rand, rand1, rand2, arithmetic);
    }

    public static int determineDifficultyLevel(Scanner scan) {

        int difficultyLevel = scan.nextInt();
        return difficultyLevel;
    }

    // returns a random number based on a bound of number of digits (e.g. bound == 10 means 1 digit number (0 - 9))
    public static int getRandomInt(SecureRandom rand, int bound) {
        return rand.nextInt(bound);
    }

    // gets a new problem type
    public static double arithmeticOperation(SecureRandom rand, int rand1, int rand2, int operation) {
        int randomOperation;
        double answerForSpecialCase;
        double divisionAnswerBecauseIntegerDivisionIsSilly;

        if (operation == 1) {
            System.out.printf("How much is %d + %d ?\n", rand1, rand2);
            return (rand1 + rand2);
        } else if (operation == 2) {
            System.out.printf("How much is %d * %d ?\n", rand1, rand2);
            return (rand1 * rand2);
        } else if (operation == 3) {
            System.out.printf("How much is %d - %d ?\n", rand1, rand2);
            return (rand1 - rand2);
        } else if (operation == 4) {
            System.out.printf("How much is %d / %d ?(round answer to 2 decimals)\n", rand1, rand2);
            divisionAnswerBecauseIntegerDivisionIsSilly = (double) rand1 / (double) rand2;
            return divisionAnswerBecauseIntegerDivisionIsSilly;
        } else if (operation == 5) {
            randomOperation = getRandomInt(rand, 4) + 1;

            if (randomOperation == 1) {

                answerForSpecialCase = arithmeticOperation(rand, rand1, rand2, 1);
                return answerForSpecialCase;

            } else if (randomOperation == 2) {

                answerForSpecialCase = arithmeticOperation(rand, rand1, rand2, 2);
                return answerForSpecialCase;

            } else if (randomOperation == 3) {

                answerForSpecialCase = arithmeticOperation(rand, rand1, rand2, 3);
                return answerForSpecialCase;

            } else if (randomOperation == 4) {

                answerForSpecialCase = arithmeticOperation(rand, rand1, rand2, 4);
                return answerForSpecialCase;

            }
        }
        return -1;
    }

    // Prints random positive response
    public static void positiveResponse(SecureRandom rand) {
        int random = rand.nextInt(4) + 1;

        switch (random) {
            case 1:
                System.out.println("Very good!");
                break;
            case 2:
                System.out.println("Excellent!");
                break;
            case 3:
                System.out.println("Nice work!");
                break;
            case 4:
                System.out.println("Keep up the good work!");
                break;
            default:
                System.out.println("Error: positiveResponse()");
        }
    }

    // Prints random negative response
    public static void negativeResponse(SecureRandom rand) {
        int random;
        random = rand.nextInt(4) + 1;

        switch (random) {
            case 1:
                System.out.println("No. Please try again.");
                break;
            case 2:
                System.out.println("Wrong. Try once more.");
                break;
            case 3:
                System.out.println("Don't give up!");
                break;
            case 4:
                System.out.println("No. Keep trying.");
                break;
            default:
                System.out.println("Error: negativeResponse()");
        }
    }


    // Chooses a response function from given parameter
    public static void printResponse(int responseVal, SecureRandom rand) {
        if (responseVal == 1) {
            positiveResponse(rand);
        } else {
            negativeResponse(rand);
        }
    }


    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        SecureRandom rand = new SecureRandom();

        double actualAnswer;
        double userAnswer;
        int loops = 0;
        boolean correctAnswer = false;
        int numCorrect = 0;
        int numIncorrect = 0;
        double avg;
        char choice;
        int difficulty;
        int questionType;

        final double THRESHOLD = .01;


        // Math calculation menu
        do {

            System.out.printf("--Choose a difficulty level from below--\n");
            System.out.printf("--1-- only single digit arithmetic (e.g. A*B)\n");
            System.out.printf("--2-- two digit number arithmetic  (e.g. AA*BB)\n");
            System.out.printf("--3-- three digit number arithmetic (e.g. AAA*BBB)\n");
            System.out.printf("--4-- four digit number arithmetic (e.g. AAAA*BBBB)\n");

            difficulty = determineDifficultyLevel(scan);

            System.out.printf("\n\n\n");

            System.out.printf("--Choose a what type of arithmetic--\n");
            System.out.printf("--1-- Addition(+)\n");
            System.out.printf("--2-- Multiplication(*)\n");
            System.out.printf("--3-- Subtraction(-)\n");
            System.out.printf("--4-- Division(/)\n");
            System.out.printf("--5-- Random(+,*,-,/)\n");

            questionType = scan.nextInt();

            while (loops < 10) {
                actualAnswer = newQuestion(rand, difficulty, questionType);

                userAnswer = scan.nextDouble();

//                System.out.printf("UserAnswer: %f\nActualAnswer: %f\n", userAnswer, actualAnswer);

                // Change here boys
//                    if (userAnswer == actualAnswer)
                if (Double.compare(actualAnswer, userAnswer) == 0 || (Math.abs(userAnswer - actualAnswer) < THRESHOLD)) {
//                    if(Double.compare(userAnswer,actualAnswer) == 0){
                    printResponse(1, rand);
                    numCorrect++;
                } else {
                    numIncorrect++;
                    printResponse(0, rand);
                }

                loops++;
            }

            // average calculations
            avg = numCorrect / (double) 10;
            avg *= 100;

            System.out.printf("Correct: %d\nIncorrect: %d\n\n", numCorrect, numIncorrect);
            System.out.printf("You scored a %.0f\n", avg);

            // responses for results of test
            if (avg < 75) {
                System.out.println("Please ask your teacher for extra help.");
            } else {
                System.out.println("Congratulations, you are ready to go to the next level!");
            }

            // reset the program
            numCorrect = 0;
            numIncorrect = 0;
            loops = 0;

            System.out.printf("Would you like to try again?\nIf so, enter 'y'.\nEnter 'q' to quit\n");
            choice = scan.next().charAt(0);

        } while (choice != 'q');


    }
}


