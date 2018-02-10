

import java.util.ArrayList;

public class Factory {

    //factory is a singleton
    private static final Factory INSTANCE = new Factory();
    //list of all Machines in the factory
    private ArrayList<Machine> Machines;


    private Factory() {
        Machines = new ArrayList<>();
    }

    public static Factory getInstance() {
        return INSTANCE;
    }

    public void createMachine(String Name,String Id)
    {
        if(!machineIdExists(Id))
        {
            Machine machine = new Machine(Name,Id);
            Machines.add(machine);
        }
        else System.out.println("Machine ID already exists");

    }

    public boolean machineIdExists(String Id)
    {
        for (Machine m : Machines)
        {
            if (m.getId().equals(Id)) return true;
        }
        return false;
    }

    public void listMachines()
    {
        for (Machine m : Machines)
        {
            System.out.println("machine name : "+m.getName()+" machine id : "+m.getId());
        }
    }

    public Machine getMachine(String Id)
    {
        for (Machine m : Machines)
        {
            if (m.getId().equals(Id)) return m;
        }
        return null;
    }
}
