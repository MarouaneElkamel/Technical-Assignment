import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryTest {

    Factory testFactory;
    @Before
    public void setUp() throws Exception {
       testFactory = Factory.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        testFactory = null;
    }

    @Test
    public void getInstance() {
        assertNotNull(testFactory);
    }

    @Test
    public void createMachine() {
        testFactory.createMachine("TestName","TestID");
        assertNotNull(testFactory.getMachine("TestID"));
    }

    @Test
    public void machineIdExists() {
        testFactory.createMachine("TestName1","TestID1");
        assertTrue(testFactory.machineIdExists("TestID1"));
    }

    @Test
    public void getMachine() {
        testFactory.createMachine("TestName2","TestID2");
        assertNotNull(testFactory.getMachine("TestID2"));
    }

    @Test
    public void getNotExistingMachine()
    {

        assertNull(testFactory.getMachine("notExist"));
    }
}