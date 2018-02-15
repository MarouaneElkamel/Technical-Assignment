package command;

public class Machine implements MachineInterface {

    private String name;
    private String id;
    private int unitNumber;
    private int temperature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //addTimes record how many times we've added units
    //purpose : calculate the average = unitNumber/addTimes
    private double addTimes;

    //public constructor that assign the name and id and initialize units and temperature
    public Machine(String name, String id) {
        this.name = name;
        this.id = id;
        unitNumber = 0;
        temperature = 0;
        addTimes = 0;
    }

    @Override
    public void addUnit(int unitNumber) {
        this.unitNumber += unitNumber;
        addTimes++;
    }

    @Override
    public int getTotalUnitsNumber() {
        return unitNumber;
    }

    @Override
    public double getAverage() {
        return (unitNumber/addTimes);
    }

    @Override
    public void setTemperature(int tmp) {
        this.temperature=tmp;
    }

    @Override
    public int getTemperature() {
        return this.temperature;
    }
}
