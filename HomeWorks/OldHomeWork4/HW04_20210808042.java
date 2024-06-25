import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HW04_20210808042 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class Computer {
    protected CPU cpu;
    protected RAM ram;

    public Computer(CPU cpu, RAM ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public void run() throws ComputationException, MemoryException, InterruptedException {
        try {
            int[] diagonalValues = ram.getDiagonalValues();
            int sum = 0;
            for (int value : diagonalValues) {
                sum = cpu.compute(sum, value);
            }
            ram.setValue(0, 0, sum);
        } catch (ComputationException e) {
            cpu.fixComputation(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Computer: " + cpu + " " + ram;
    }
}

class Laptop extends Computer {
    private int milliAmp;
    private int battery;

    public Laptop(CPU cpu, RAM ram, int milliAmp) {
        super(cpu, ram);
        this.milliAmp = milliAmp;
        this.battery = milliAmp * 30 / 100;
    }

    public int batteryPercentage() {
        return this.battery * 100 / this.milliAmp;
    }

    public void charge() {
        while (batteryPercentage() < 90) {
            this.battery += milliAmp * 2 / 100;
        }
    }

    @Override
    public void run() throws ComputationException, MemoryException {
        if (batteryPercentage() > 5) {
            super.run();
            this.battery -= milliAmp * 3 / 100;
        } else {
            charge();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + battery;
    }
}

class Desktop extends Computer {
    private ArrayList<String> peripherals;

    public Desktop(CPU cpu, RAM ram, String... peripherals) {
        super(cpu, ram);
        this.peripherals = new ArrayList<>();
        for (String peripheral : peripherals) {
            this.peripherals.add(peripheral);
        }
    }

    @Override
    public void run() throws ComputationException, MemoryException {
        try {
            int[] allValues = ram.getAllValues();
            int sum = 0;
            for (int value : allValues) {
                sum = cpu.compute(sum, value);
            }
            ram.setValue(0, 0, sum);
        } catch (ComputationException e) {
            cpu.fixComputation(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void plugIn(String peripheral) {
        peripherals.add(peripheral);
    }

    public String plugOut() {
        return peripherals.remove(peripherals.size() - 1);
    }

    public String plugOut(int index) {
        return peripherals.remove(index);
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.join(" ", peripherals);
    }
}

class CPU {
    private String name;
    private double clock;

    public CPU(String name, double clock) {
        this.name = name;
        this.clock = clock;
    }

    public String getName() {
        return name;
    }

    public double getClock() {
        return clock;
    }

    public int compute(int a, int b) throws ComputationException, InterruptedException {
        Thread.sleep((long) (5 / clock * 1000));
        int result = a + b;
        if (result < 0) {
            throw new ComputationException(this, a, b);
        }
        return result;
    }

    public int fixComputation(ComputationException e) throws ComputationException, InterruptedException {
        return e.fixComputation(e.value1, e.value2);
    }

    @Override
    public String toString() {
        return "CPU: " + name + " " + clock + "Ghz";
    }
}

class RAM {
    private String type;
    private int capacity;
    private int[][] memory;

    public RAM(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        initMemory();
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    private void initMemory() {
        memory = new int[capacity][capacity];
        Random random = new Random();
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                memory[i][j] = random.nextInt(11);
            }
        }
    }

    private boolean check(int i, int j) throws MemoryException {
        if (i < 0 || i >= capacity || j < 0 || j >= capacity) {
            throw new MemoryException(this, i, j);
        }
        return true;
    }

    public int getValue(int i, int j) throws MemoryException {
        if (check(i, j)) {
            return memory[i][j];
        }
        return -1;
    }

    public void setValue(int i, int j, int value) throws MemoryException {
        if (check(i, j)) {
            memory[i][j] = value;
        }
    }

    @Override
    public String toString() {
        return "RAM: " + type + " " + capacity + "GB";
    }
}

class MemoryException extends RuntimeException {
    private RAM ram;
    private int address1;
    private int address2;

    public MemoryException(RAM ram, int address1, int address2) {
        super("Memory out of range! With addresses #" + address1 + ", " + address2);
        this.ram = ram;
        this.address1 = address1;
        this.address2 = address2;
    }
}

class ComputationException extends Exception {
    private CPU cpu;
    private int value1;
    private int value2;

    public ComputationException(CPU cpu, int value1, int value2) {
        super("Computation exception occurred on " + cpu + " with values: (" + value1 + ", " + value2 + ")");
        this.cpu = cpu;
        this.value1 = value1;
        this.value2 = value2;
    }

    public ComputationException(ComputationException e) {
        super("Unhandled exception occurred at " + e.cpu + " with values " + e.value1 + " and " + e.value2 + ":\n\t"
                + e.getMessage());
    }

    public int fixComputation(int val1, int val2) throws ComputationException {
        try {
            return cpu.compute(Math.abs(val1), Math.abs(val2));
        } catch (ComputationException e) {
            throw new ComputationException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
