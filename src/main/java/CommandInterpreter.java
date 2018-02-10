
import java.util.ArrayList;
import java.util.Arrays;

public class CommandInterpreter {

    String firstArgument,secondArgument,thirdArgument;
    int error;
    ArrayList<String> validCommand ;

    //public constructor
    public CommandInterpreter() {
        //add the list of all valid command
        validCommand = new ArrayList<>();
        validCommand.add("create");
        validCommand.add("add");
        validCommand.add("temperature");
        validCommand.add("total");
        validCommand.add("average");
        validCommand.add("list");
    }


    public String Interpret(String command) {


        command.trim();
        error=0;
        //split command into arguments
        String[] words = command.split(" ");
        if(words.length>=1) this.firstArgument = words[0];
        else error=1;
        if(words.length>=2) this.secondArgument = words[1];
        if(words.length>=3) this.thirdArgument = words[2];
        else thirdArgument = "";

        //check if the command is valid
        boolean test = IsValid();
        if (!test || error==1)
        {
            System.out.println("Command Error");
            return "Error";
        }
        else
        {
            //execute the command if it's valid
            return executeCommand();
        }
    }

    private boolean IsValid()

    {
        if (firstArgument.equals("list") ) return true;
        //check command in command dictionary
        if (!validCommand.contains(firstArgument))
        {
            error=1;
            System.out.println("Command unknown");
            return false;
        }
        //check Machine exists
        if (!firstArgument.equals("create") && !Factory.getInstance().machineIdExists(secondArgument))
        {
            error=1;
            System.out.println("Machine Id not found");
            return false;
        }

        //check Machine exists
        if (firstArgument.equals("create") && Factory.getInstance().machineIdExists(thirdArgument))
        {
            error=1;
            System.out.println("Machine Id already exists");
            return false;
        }

        //check that there is a third argument for create and add
        if (firstArgument.equals("create") ||firstArgument.equals("add") )
        {
            if (thirdArgument.equals("") )
            {
                System.out.println("Missing third argument");
                error=1;
                return false;
            }
        }



        //check that there is no third argument in average and total
        if (firstArgument.equals("average") || firstArgument.equals("total")  )
        {
            if (!thirdArgument.equals("") )
            {
                System.out.println("found third Argument but not needed one");
                error=1;
                return false;
            }
        }

        //check if the third argument is an integer in case the command is add or temperature
        if (firstArgument.equals("add") || (firstArgument.equals("temperature") && !thirdArgument.equals("")))
        {
            try {
                Integer.parseInt(thirdArgument);
            } catch(Exception e) {
                System.out.println("Third argument must be integer ");
                error=1;
                return false;
            }
        }



        return true;
    }

    private String executeCommand() {
        Machine m;
        String result="";
        //execute command based on argument
        switch (firstArgument) {
            case "create":
                Factory.getInstance().createMachine(secondArgument, thirdArgument);
                break;
            case "add":
                m = Factory.getInstance().getMachine(secondArgument);
                m.addUnit(Integer.parseInt(thirdArgument));
                break;
            case "temperature":
                m = Factory.getInstance().getMachine(secondArgument);
                if (thirdArgument.equals("")) result= String.valueOf(m.getTemperature());
                else m.setTemperature(Integer.parseInt(thirdArgument));
                break;
            case "total":
                m = Factory.getInstance().getMachine(secondArgument);
                result= String.valueOf(m.getTotalUnitsNumber());
                break;
            case "average":
                m = Factory.getInstance().getMachine(secondArgument);
                result= String.valueOf(m.getAverage());
            case "list":
                Factory.getInstance().listMachines();
        }

        return result;
    }
}
