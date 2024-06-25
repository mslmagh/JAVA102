abstract class Computer {
    protected CPU cpu;
    protected RAM ram;
 
     Computer(CPU cpu, RAM ram) {
        this.cpu = cpu;
        this.ram = ram;
    }
     public void run() {
        for (int i = 0; i< ram.getCapacity(); i++) {
            for( int j = 0; j<ram.getCapacity(); j++) {
                int address1 = ram.getValue(0, 0);
                int address2 = ram.getValue(i, i);
                int result = -1;
               
                try {
                    result = cpu.compute(address1, address2);
                }
                catch (ComputationExeption e) {
                    e.printStackTrace();
                }
                ram.setValue(i, j, result);
            }
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
    public String toString() {
        return "Computer: " + cpu + " " + ram;
    }
 
}class CPU {
    private String name;
    private double clock;
 
    public CPU(String name, double clock) {
        this.name = name;
        this.clock = clock;
    }
 
    public String getName() {
        return "CPU: " + name + ", " + clock + "Ghz";
    }
    public double getClock() {
        return clock;
    }
    public int compute(int a, int b) throws ComputationExeption {
        try {
            Thread.sleep((int)(10 / clock) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = a +b;
        if(result < 0) {
            throw new CompletionException(null);
        }
        return a +b;
    }
 
}
class RAM {
    private String type;
    private int capacity;
    private int[][] memory;
 
    RAM(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }
   
    public void initMemory(RAM ram) {
        Random rnd = new Random();
        memory = new int[capacity][capacity];
        for(int i=0; i<capacity; i++) {
            for(int j=0; j<capacity; j++) {
                memory[i][j] = rnd.nextInt(10);
            }
        }
    }
    public int getCapacity() {
        return capacity;
    }
    public String getType() {
        return type;
    }
    private boolean check(int i, int j) {
        if(i<0 || i>= capacity || j<0 || j>= capacity) {
            throw new ArrayIndexOutOfBoundsException();
           
        }
        else return true;
    }
 
    public int getValue(int i, int j) {
        check(i, j);
        return memory[i][j];
    }
 
    public void setValue(int i , int j, int value)  throws IndexOutOfBoundsException{
        check(i, j);
        memory[i][j] =value;
    }
    @Override
    public String toString() {
        return "RAM: " + type + " " + capacity + "GB ";
    }
}
class MemoryException extends RuntimeException {
    private RAM ram;
   
    MemoryException(RAM ram, int i, int j ) {
        super("Inddexes are out of the bounds of the memory for indexes " + i + j);
        this.ram =ram;
    }
 
}
class ComputationExeption extends Exception {
    private CPU cpu;
 
    ComputationExeption(CPU cpu, int result) {
        super("an exception occured");
        this.cpu = cpu;
 
    }
}