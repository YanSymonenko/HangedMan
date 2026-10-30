import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangedman {
    public static void main(String[] args){

        String filePath = "words.txt";
        ArrayList<String> words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                words.add(line.trim());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Could not find file");
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }
        Random random = new Random();

        String word = words.get(random.nextInt(words.size()));

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        int WrongGuess = 0;

        for(int i = 0; i < word.length(); i++){
            wordState.add('_');
        }
        System.out.println("||||||||||||||||||||||||||||");
        System.out.println("Test your luck with Hangman!");
        System.out.println("||||||||||||||||||||||||||||");

        while(WrongGuess < 6){

            System.out.print(getHangman(WrongGuess));

            System.out.print("Word: ");

            for(char c : wordState){
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.println("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if(word.indexOf(guess) >= 0){
                System.out.println("Good call! Correct!");

                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == guess){
                        wordState.set(i, guess);
                    }
                }
                if(!wordState.contains('_')){
                    System.out.println(getHangman(WrongGuess));
                    System.out.println("Oh my lord... You got it!");
                    System.out.println("The word was: " + word);
                    break;
                }
            }
            else{
                WrongGuess++;
                System.out.println("Oof... Wrong one!");
            }
        }
        if(WrongGuess >= 6){
            System.out.println(getHangman(WrongGuess));
            System.out.println("Tough luck, cowboy...");
            System.out.println("The word was: " + word);
        }

        scanner.close();

    }
    static String getHangman(int wrongGuess){
        return switch(wrongGuess){
            case 0 -> """
                      
                      
                      
                      """;
            case 1 -> """
                       0
                      
                      
                      """;
            case 2 -> """
                       0
                       |
                      
                      """;
            case 3 -> """
                       0
                      /|
                      
                      """;
            case 4 -> """
                       0
                      /|\\
                      
                      """;
            case 5 -> """
                       0
                      /|\\
                      /
                      """;
            case 6 -> """
                       0
                      /|\\
                      / \\
                      """;
            default -> "";
        };
    }
}
