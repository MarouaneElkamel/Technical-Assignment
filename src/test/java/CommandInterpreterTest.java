import command.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandinterpreterTest {
    CommandInterpreter testCM;
    @Before
    public void setUp() throws Exception {
        testCM = new CommandInterpreter();

    }

    @After
    public void tearDown() throws Exception {
        testCM = null;
    }

    @Test
    public void createTest()
    {
        testCM.interpret("create TestMachine1 TestCreateID1");
        assertNotNull(Factory.getInstance().getMachine("TestCreateID1"));
    }

    @Test
    public void addTest()
    {    testCM.interpret("create TestMachine2 TestCreateID2");
        int initial = Factory.getInstance().getMachine("TestCreateID2").getTotalUnitsNumber();
        testCM.interpret("add TestCreateID2 20");
        int after = Factory.getInstance().getMachine("TestCreateID2").getTotalUnitsNumber();
        assertEquals(20,after-initial);
    }

    @Test
    public void temperatureSetTest()
    {
        testCM.interpret("create TestMachine3 TestCreateID3");
        int initial = Factory.getInstance().getMachine("TestCreateID3").getTemperature();
        testCM.interpret("temperature TestCreateID3 20");
        int after = Factory.getInstance().getMachine("TestCreateID3").getTemperature();
        assertEquals(20,after-initial);
    }

    @Test
    public void temperatureShowTest()
    {
        testCM.interpret("create TestMachine4 TestCreateID4");
        int initial = Factory.getInstance().getMachine("TestCreateID4").getTemperature();

        int after = Integer.parseInt(testCM.interpret("temperature TestCreateID4"));;
        assertEquals(initial,after);
    }

    @Test
    public void totalTest()
    {
        testCM.interpret("create TestMachine5 TestCreateID5");
        int initial = Factory.getInstance().getMachine("TestCreateID5").getTotalUnitsNumber();

        int after = Integer.parseInt(testCM.interpret("total TestCreateID5"));;
        assertEquals(initial,after);
    }
    @Test
    public void averageTest()
    {
        testCM.interpret("create TestMachine6 TestCreateID6");
        double initial = Factory.getInstance().getMachine("TestCreateID6").getAverage();

        double after = Double.parseDouble(testCM.interpret("average TestCreateID6"));;
        assertEquals((int)initial,(int)after);
    }
    @Test
    public void zeroArgTest()
    {
        String result = testCM.interpret("");
        assertEquals("Error",result);
    }


    @Test
    public void oneArgTest()
    {
        String result = testCM.interpret("create");
        assertEquals("Error",result);
    }

    @Test
    public void twoArgTest()
    {
        String result = testCM.interpret("create Machine");
        assertEquals("Error",result);
    }

    @Test
    public void createExistingTest()
    {
        String result;
        Factory.getInstance().createMachine("TestMachine","TestCreateIDx");
        result = testCM.interpret("create TestMachine TestCreateIDx");
        assertEquals("Error",result);
    }

    @Test
    public void addWithoutThirdArgumentTest()
    {
        String result;
        result = testCM.interpret("create TestMachine8 TestCreateID8");
        result = testCM.interpret("add TestCreateID8");
        assertEquals("Error",result);
    }
    @Test
    public void createWithoutThirdArgumentTest()
    {
        String result;
        result = testCM.interpret("create TestMachine8");
        assertEquals("Error",result);
    }

    @Test
    public void totalWithThirdArgumentTest()
    {
        String result;
        testCM.interpret("create TestMachine9 TestMachine9");
        result = testCM.interpret("total TestMachine9 20");
        assertEquals("Error",result);
    }

    @Test
    public void averageWithThirdArgumentTest()
    {
        String result;
        Factory.getInstance().createMachine("TestMachine10", "TestMachine10");
        result = testCM.interpret("average TestMachine10 20");
        assertEquals("Error",result);
    }

    @Test
    public void thirdArgumentNotInt()
    {
        String result;
        result = testCM.interpret("create TestMachine11 TestCreateID11");
        result = testCM.interpret("add TestCreateID11 T");
        assertEquals("Error",result);
    }

    @Test
    public void machineIdNotFoundTest()
    {
        String result;
        result = testCM.interpret("add TestMachineZ 2");
        assertEquals("Error",result);
    }

}