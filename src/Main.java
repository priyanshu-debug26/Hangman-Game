import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String [] args){

        // Hangman Game

        String filePath = "src/Character.txt";
        ArrayList<String> character = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                character.add(line.trim());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Something Went Wrong!");
        }

        Random random = new Random();

        String word = character.get(random.nextInt(character.size()));

        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> blank = new ArrayList<>();
        int wrongGuess = 0;

        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == ' '){
                blank.add(' ');
            }
            else{
                blank.add('_');
            }
        }

        System.out.print("------------HANGMAN GAME------------");
        System.out.println();

        while(wrongGuess < 6){

            System.out.print(hangman(wrongGuess));

            System.out.print("Word: ");

            for(char c: blank){
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Guess a letter: ");
            char guess = scanner.next().toUpperCase().charAt(0);

            if(word.indexOf(guess) >= 0){
                System.out.println("Correct Guess!!\n");

                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == guess){
                        blank.set(i , guess);
                    }
                }
                if(!blank.contains('_')){
                    System.out.print(hangman(wrongGuess));
                    System.out.println("YOU WIN!!!!!!");
                    System.out.println("The word was indeed : " + word);
                    break;
                }
            }
            else{
                wrongGuess++;
                System.out.println("Wrong Guess!!");
            }
        }

        if(wrongGuess >= 6){
            System.out.print(hangman(wrongGuess));
            System.out.println("THE GAME IS OVER YOU LOST \nYOU NOOB");
            System.out.print("The word was: " + word);
        }
       scanner.close();
    }
    static String hangman (int wrongGuess){
        return switch(wrongGuess){
            case 0 -> """
                      """;
            case 1 -> """
                       o
                    
                    
                      """;
            case 2 -> """
                       o
                       |
                    
                      """;
            case 3 -> """
                       o
                      /|
                    
                      """;
            case 4 -> """
                       o
                      /|\\
                    
                      """;
            case 5 -> """
                       o
                      /|\\
                      /
                      """;
            case 6 -> """
                       o
                      /|\\
                      / \\
                      """;
            default -> "";

        };
    }
}
