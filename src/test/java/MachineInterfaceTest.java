import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MachineInterfaceTest {
    MachineInterface testMachine;

    @Before
    public void setUp() throws Exception {
      testMachine = new Machine("Machine","Id");
    }

    @After
    public void tearDown() throws Exception {
    testMachine = null;
    }

    @Test
    public void addUnit() {
        int initial = testMachine.getTotalUnitsNumber();
        testMachine.addUnit(5);
        int after = testMachine.getTotalUnitsNumber();
        assertEquals(5,after-initial);
    }

    @Test
    public void getTotalUnitsNumber() {
        int initial = testMachine.getTotalUnitsNumber();
        assertEquals(0,initial);
    }

    @Test
    public void getAverage() {
        testMachine.addUnit(0);
        testMachine.addUnit(0);
        assertEquals(0,(int)testMachine.getAverage());
    }

    @Test
    public void setTemperature() {
        testMachine.setTemperature(120);
        assertEquals(120,testMachine.getTemperature());
    }

    @Test
    public void getTemperature() {
        assertEquals(0,testMachine.getTemperature());
    }
}