
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Lab07UnitTesting_Test {

    private static int testNumber = 0;

    @BeforeClass
    public static void setUp() {
        System.out.println("Beginnig testing");
    }

    @Before
    public void display() {
        System.out.println("\n Running test: " + (++testNumber));
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("End of tests");
    }
    @After
    public void displayAfter() {
        System.out.println("After the test: " + testNumber);
    }

    @Test
    public void shouldCreateAccountObject() {
        System.out.println("Should be able to instantiate an Account object");
        try {
            Account account = new Account("1234", -1);
        } catch (InsufficientFundsException e) {
            fail("Couldn't create an instance of the Account class");
        }
        
       
    }
}
