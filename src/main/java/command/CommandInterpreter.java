package command;

import java.util.ArrayList;

public class CommandInterpreter {

    String firstArgument;
    String secondArgument;
    String thirdArgument;
    int error;
    final static String CREATE_COMMAND = "create";
    final static String ADD_COMMAND = "add";
    final static String TEMPERATURE_COMMAND = "temperature";
    final static String TOTAL_COMMAND = "total";
    final static String AVERAGE_COMMAND = "average";
    final static String LIST_COMMAND = "list";
    ArrayList<String> validCommand ;

    //public constructor
    public CommandInterpreter() {
        //add the list of all valid command
        validCommand = new ArrayList<>();
        validCommand.add(CREATE_COMMAND);
        validCommand.add(ADD_COMMAND);
        validCommand.add(TEMPERATURE_COMMAND);
        validCommand.add(TOTAL_COMMAND);
        validCommand.add(AVERAGE_COMMAND);
        validCommand.add(LIST_COMMAND);
    }


    public String interpret(String command) {


        command = command.trim();
        error=0;
        //split command into arguments
        String[] words = command.split(" ");
        if(words.length>=1) this.firstArgument = words[0];
        else error=1;
        if(words.length>=2) this.secondArgument = words[1];
        if(words.length>=3) this.thirdArgument = words[2];
        else thirdArgument = "";

        //check if the command is valid
        boolean test = isValid();
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

    private boolean isValid()

    {
        if (firstArgument.equals(LIST_COMMAND) ) return true;
        //check command in command dictionary
        if (!validCommand.contains(firstArgument))
        {
            error=1;
            System.out.println("Command unknown");
            return false;
        }
        //check Machine exists
        if (!firstArgument.equals(CREATE_COMMAND) && !Factory.getInstance().machineIdExists(secondArgument))
        {
            error=1;
            System.out.println("Machine Id not found");
            return false;
        }

        //check Machine exists
        if (firstArgument.equals(CREATE_COMMAND) && Factory.getInstance().machineIdExists(thirdArgument))
        {
            error=1;
            System.out.println("Machine Id already exists");
            return false;
        }

        //check that there is a third argument for create and add
        if ((firstArgument.equals(CREATE_COMMAND) ||firstArgument.equals(ADD_COMMAND)) && thirdArgument.equals("")  )
        {

                System.out.println("Missing third argument");
                error=1;
                return false;
        }



        //check that there is no third argument in average and total
        if (firstArgument.equals(AVERAGE_COMMAND) || firstArgument.equals(TOTAL_COMMAND)  )
        {
            if (!thirdArgument.equals("") )
            {
                System.out.println("found third Argument but not needed one");
                error=1;
                return false;
            }
        }

        //check if the third argument is an integer in case the command is add or temperature
        if (firstArgument.equals(ADD_COMMAND) || (firstArgument.equals(TEMPERATURE_COMMAND) && !thirdArgument.equals("")))
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
            case CREATE_COMMAND:
                Factory.getInstance().createMachine(secondArgument, thirdArgument);
                break;
            case ADD_COMMAND:
                m = Factory.getInstance().getMachine(secondArgument);
                m.addUnit(Integer.parseInt(thirdArgument));
                break;
            case TEMPERATURE_COMMAND:
                m = Factory.getInstance().getMachine(secondArgument);
                if (thirdArgument.equals("")) result= String.valueOf(m.getTemperature());
                else m.setTemperature(Integer.parseInt(thirdArgument));
                break;
            case TOTAL_COMMAND:
                m = Factory.getInstance().getMachine(secondArgument);
                result= String.valueOf(m.getTotalUnitsNumber());
                break;
            case AVERAGE_COMMAND:
                m = Factory.getInstance().getMachine(secondArgument);
                result= String.valueOf(m.getAverage());
                break;
            case LIST_COMMAND:
                Factory.getInstance().listMachines();
                break;
            default:
                System.out.println("nope");
        }

        return result;
    }
}
