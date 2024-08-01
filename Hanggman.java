import java.util.Scanner;

public class Hanggman {
    private static final String[] WORDS = {"java", "programming", "computer", "hangman", "game"};
    private static final int MAX_TRIES = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = selectRandomWord();
        char[] guessedLetters = new char[word.length()];
        int tries = 0;
        boolean wordGuessed = false;

        initializeGuessedLetters(guessedLetters);

        System.out.println("Welcome to Hangman!");
        while (tries < MAX_TRIES && !wordGuessed) {
            displayHangman(tries);
            displayWord(guessedLetters);

            System.out.print("Enter a letter: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            if (isLetterAlreadyGuessed(guess, guessedLetters)) {
                System.out.println("You've already guessed that letter!");
                continue;
            }

            if (isLetterInWord(guess, word)) {
                updateGuessedLetters(guess, word, guessedLetters);
                if (isWordGuessed(guessedLetters)) {
                    wordGuessed = true;
                    System.out.println("Congratulations! You've guessed the word: " + word);
                }
            } else {
                tries++;
                System.out.println("Incorrect guess. " + (MAX_TRIES - tries) + " tries left.");
            }
        }
        scanner.close();

        if (!wordGuessed) {
            System.out.println("Sorry, you've run out of tries! The word was: " + word);
        }
    }

    private static String selectRandomWord() {
        return WORDS[(int) (Math.random() * WORDS.length)];
    }

    private static void initializeGuessedLetters(char[] guessedLetters) {
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
    }

    private static void displayHangman(int tries) {
        String[] hangman = {
            "  +---+",
            "  |   |",
            "  O   |",
            " /|\\  |",
            " / \\  |",
            "      |"
        };

        System.out.println("=========");
        for (int i = 0; i < tries; i++) {
            System.out.println(hangman[i]);
        }
        System.out.println("=========");
    }

    private static void displayWord(char[] guessedLetters) {
        for (char c : guessedLetters) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private static boolean isLetterAlreadyGuessed(char guess, char[] guessedLetters) {
        for (char c : guessedLetters) {
            if (c == guess) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLetterInWord(char guess, String word) {
        return word.indexOf(guess) != -1;
    }

    private static void updateGuessedLetters(char guess, String word, char[] guessedLetters) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedLetters[i] = guess;
            }
        }
    }

    private static boolean isWordGuessed(char[] guessedLetters) {
        for (char c : guessedLetters) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

}
