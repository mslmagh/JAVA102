import java.util.ArrayList;
import java.util.Random;

public class HW04_20210808042 {
    public static void main(String[] args) {
        CPU cpu = new CPU("Intel i7", 3.5);
        RAM ram = new RAM("DDR4", 16);
        Computer computer = new Computer(cpu, ram);
        System.out.println(computer);

        Laptop laptop = new Laptop(cpu, ram, 5000);
        System.out.println(laptop);

        Desktop desktop = new Desktop(cpu, ram, "Keyboard", "Mouse");
        System.out.println(desktop);
    }
}

class Computer {
    protected CPU cpu;
    protected RAM ram;

    public Computer(CPU cpu, RAM ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public void run() throws ComputationException, MemoryException {
        try {
            int sum = 0;
            for (int i = 0; i < ram.getCapacity(); i++) {
                sum += ram.getValue(i, i);
            }
            cpu.compute(sum, sum);
        } catch (ComputationException e) {
            fixComputation(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fixComputation(ComputationException e) {
        int val1 = Math.abs(e.getValue1());
        int val2 = Math.abs(e.getValue2());
        try {
            cpu.compute(val1, val2);
        } catch (ComputationException ce) {
            ce.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Computer: " + cpu + " " + ram;
    }
}

class Laptop extends Computer {
    public int milliAmp;
    private int battery;

    public Laptop(CPU cpu, RAM ram, int milliAmp) {
        super(cpu, ram);
        this.milliAmp = milliAmp;
        this.battery = (int) (0.3 * milliAmp);
    }

    public int batteryPercentage() {
        return (battery * 100) / milliAmp;
    }

    public void charge() {
        while (batteryPercentage() < 90) {
            battery += milliAmp * 0.02;
        }
    }

    @Override
    public void run() throws ComputationException, MemoryException {
        if (batteryPercentage() > 5) {
            super.run();
            battery -= milliAmp * 0.03;
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
    ArrayList<String> peripherals;

    public Desktop(CPU cpu, RAM ram, String... peripherals) {
        super(cpu, ram);
        this.peripherals = new ArrayList<>();
        for (String peripheral : peripherals) {
            this.peripherals.add(peripheral);
        }
    }

    public void plugIn(String peripheral) {
        peripherals.add(peripheral);
    }

    public String plugOut() {
        if (!peripherals.isEmpty()) {
            return peripherals.remove(peripherals.size() - 1);
        }
        return null;
    }

    public String plugOut(int index) {
        if (index >= 0 && index < peripherals.size()) {
            return peripherals.remove(index);
        }
        return null;
    }

    @Override
    public void run() throws ComputationException, MemoryException {
        int sum = 0;
        for (int i = 0; i < ram.getCapacity(); i++) {
            for (int j = 0; j < ram.getCapacity(); j++) {
                sum += ram.getValue(i, j);
            }
        }
        try {
            cpu.compute(sum, sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        for (String peripheral : peripherals) {
            sb.append(" ").append(peripheral);
        }
        return sb.toString();
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
        try {
            Thread.sleep((long) (5 / clock * 1000));
            int result = a + b;
            if (result < 0) {
                throw new ComputationException(this, a, b);
            }
            return result;
        } catch (InterruptedException e) {
            throw new InterruptedException("Exception occurred due to internal clock speed: " + clock);
        }
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
    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

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
        } catch (ComputationException ce) {
            throw ce;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
