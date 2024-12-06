//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File dictionary = new File("wordlist.10000.txt");
        Hash.SeparateChainingHash chain = new Hash.SeparateChainingHash();
        Hash.LinearProbingHashST linear = new Hash.LinearProbingHashST();
        Scanner reader = new Scanner(dictionary);
        while(reader.hasNextLine()){
            String word = reader.nextLine();
            chain.put(word, word.length());
            linear.put(word, word.length());
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Choose your password:");
        String password = scan.nextLine();

        boolean IsInChain = chain.contains(password);
        boolean IsInLinear = linear.contains(password);
        boolean goodpass = true;

        if(password.length() >= 8 && !IsInChain && !IsInLinear){
             for(int i =0; i < 10; i++){
                 String mod = password.substring(0, password.length()-1);
                 if(chain.contains(mod) || linear.contains(mod) && password.endsWith(String.valueOf(i))){
                     System.out.println("Your password is not strong enough.");
                     goodpass = false;
                     break;
                 }
             }
             if(goodpass){
                 System.out.println("You have a strong password!");
             }
        }else{
            System.out.println("Your password is not strong enough.");
        }

        chain.get(password);
        linear.get(password);




    }
}