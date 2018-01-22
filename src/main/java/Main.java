

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String command="";
        CommandInterpreter commandInterpreter = new CommandInterpreter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Factory");
        System.out.println("if you want to exit the app type q");
        while (true)
        {
            command=scanner.nextLine();
            String result="";
            if(command.equals("q")) break;
            else
            {
                result = commandInterpreter.Interpret(command);
                if (!result.equals("")) System.out.println(result);
            }
        }

    }
}
