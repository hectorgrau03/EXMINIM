package edu.upc.dsa;

import edu.upc.dsa.models.Institute;
import edu.upc.dsa.models.Operation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class MathManagerTest {
    private MathManager manager;

    @Before
    public void setUp() {
        this.manager = MathManagerImpl.getInstance();

        this.manager.clear();

        manager.addOperation("5 1 2 + 4 * + 3 -", "1", "1");
        manager.addOperation("10 2 /", "2", "2");
        manager.addOperation("2 2 +", "3", "3");
    }

    @After
    public void tearDown() {
        this.manager = null;
    }

    @Test
    public void testProcessOperation() {
        Operation op = manager.processNextOperation();

        Assert.assertNotNull("Operación nula", op);
        Assert.assertEquals("1", op.getStudentId());
        Assert.assertEquals(14.0, op.getResult(), 0.01);
    }

    @Test
    public void testGetOperationsByStudent() {
        manager.processNextOperation();
        manager.processNextOperation();

        List<Operation> ops = manager.getOperationsByStudent("1");
        Assert.assertEquals(1, ops.size());
        Assert.assertEquals("1", ops.get(0).getStudentId());
    }

    @Test
    public void testInstituteRanking() {
        manager.processNextOperation();
        manager.processNextOperation();
        manager.processNextOperation();

        List<Institute> ranking = manager.getInstitutesByOperationsDesc();

        Assert.assertEquals(3, ranking.size());
        Assert.assertEquals(1, ranking.get(0).getOperationsCount());
    }
}
