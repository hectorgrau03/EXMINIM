package edu.upc.dsa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReversePolishNotationTest {

    private ReversePolishNotation RPN;

    @Before
    public void setUp() {
        // Inicializamos el componente antes de cada test
        this.RPN = new ReversePolishNotationImpl();
    }

    @After
    public void tearDown() {
        // Limpiamos después de cada test
        this.RPN = null;
    }

    @Test
    public void testProcessSimpleAddition() {
        double result = RPN.NotacionPolaca("2 2 +");
        Assert.assertEquals(4.0, result, 0.01);
    }

    @Test
    public void testProcessComplexExpression() {
        double result = RPN.NotacionPolaca("5 1 2 + 4 * + 3 -");
        Assert.assertEquals(14.0, result, 0.01);
    }

    @Test
    public void testProcessDivision() {
        double result = RPN.NotacionPolaca("10 2 /");
        Assert.assertEquals(5.0, result, 0.01);
    }

    @Test
    public void testProcessEmptyString() {
        double result = RPN.NotacionPolaca("");
        Assert.assertEquals(0.0, result, 0.01);
    }
}