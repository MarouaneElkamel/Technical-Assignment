import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import command.*;
public class MachineTest {
    Machine testMachine;
    @Before
    public void setUp() throws Exception {
        testMachine = new Machine("Machine","Id");
    }

    @After
    public void tearDown() throws Exception {
        testMachine = null;
    }

    @Test
    public void getName() {
        assertEquals("Machine",testMachine.getName());
    }

    @Test
    public void setName() {
        testMachine.setName("TEST");
        assertEquals("TEST",testMachine.getName());
    }

    @Test
    public void getId() {
        assertEquals("Id",testMachine.getId());
    }

    @Test
    public void setId() {
        testMachine.setId("TEST");
        assertEquals("TEST",testMachine.getId());
    }


}