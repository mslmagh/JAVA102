import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class HW04_20210808042Test {
    public void testRun() {
        CPU cpu = new CPU("Intel i7", 3.5);
        RAM ram = new RAM("DDR4", 16);
        Computer computer = new Computer(cpu, ram);

        try {
            computer.run();
            // If no exception is thrown, test passes
            assertTrue(true);
        } catch (ComputationException | MemoryException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
        @Test
        public void testRuni() {
            CPU cpu = new CPU("Intel i7", 3.5);
            RAM ram = new RAM("DDR4", 16);
            Desktop desktop = new Desktop(cpu, ram, "Keyboard", "Mouse");
    
            try {
                desktop.run();
                // If no exception is thrown, test passes
                assertTrue(true);
            } catch (ComputationException | MemoryException e) {
                fail("Exception occurred: " + e.getMessage());
            }
        }
        @Test
        public void testCompute() {
            CPU cpu = new CPU("Intel i7", 3.5);
    
            try {
                assertEquals(30, cpu.compute(10, 20));
            } catch (ComputationException | InterruptedException e) {
                fail("Exception occurred: " + e.getMessage());
            }
        }
    @Test
    public void testGetValueAndSetValue() {
        RAM ram = new RAM("DDR4", 16);

        try {
            assertEquals(ram.getValue(0, 0), ram.getValue(0, 0));
            ram.setValue(0, 0, 100);
            assertEquals(100, ram.getValue(0, 0));
        } catch (MemoryException e) {
            fail("Exception occurred: " + e.getMessage());
        }
}

    @Test
    public void testDesktop() {
        CPU cpu = new CPU("Intel i7", 3.5);
        RAM ram = new RAM("DDR4", 16);
        Desktop desktop = new Desktop(cpu, ram, "Keyboard", "Mouse");

        // Test toString method
        String expectedToString = "Computer: CPU: Intel i7 3.5Ghz RAM: DDR4 16GB Keyboard Mouse";
        assertEquals(expectedToString, desktop.toString());

        // Test plugIn method
        desktop.plugIn("Monitor");
        expectedToString += " Monitor";
        assertEquals(expectedToString, desktop.toString());

        // Test plugOut method (no index)
        String removedPeripheral = desktop.plugOut();
        assertEquals("Monitor", removedPeripheral);
        expectedToString = "Computer: CPU: Intel i7 3.5Ghz RAM: DDR4 16GB Keyboard Mouse";
        assertEquals(expectedToString, desktop.toString());

        // Test plugOut method (with index)
        removedPeripheral = desktop.plugOut(0);
        assertEquals("Keyboard", removedPeripheral);
        expectedToString = "Computer: CPU: Intel i7 3.5Ghz RAM: DDR4 16GB Mouse";
        assertEquals(expectedToString, desktop.toString());

        // Test run method
        assertDoesNotThrow(() -> desktop.run());
    }
    @Test
    public void testCPU() {
        CPU cpu = new CPU("Intel i7", 3.5);
        assertEquals("Intel i7", cpu.getName());
        assertEquals(3.5, cpu.getClock(), 0.001);
    }

    @Test
    public void testRAM() {
        RAM ram = new RAM("DDR4", 16);
        assertEquals("DDR4", ram.getType());
        assertEquals(16, ram.getCapacity());
    }

    @Test
    public void testComputer() {
        CPU cpu = new CPU("Intel i7", 3.5);
        RAM ram = new RAM("DDR4", 16);
        Computer computer = new Computer(cpu, ram);
        assertEquals(cpu, computer.cpu);
        assertEquals(ram, computer.ram);
    }

    @Test
    public void testLaptop() {
        CPU cpu = new CPU("Intel i7", 3.5);
        RAM ram = new RAM("DDR4", 16);
        Laptop laptop = new Laptop(cpu, ram, 5000);
        assertEquals(cpu, laptop.cpu);
        assertEquals(ram, laptop.ram);
        assertEquals(5000, laptop.milliAmp);
    }

    
}
