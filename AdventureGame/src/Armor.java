public class Armor {
    private int id;
    private int block;
    private int money;
    private String name;

    public Armor(int id, String name, int block, int money) {
        this.id = id;
        this.block = block;
        this.money = money;
        this.name = name;
    }

    public static Armor[] armors() {
        Armor[] armors = new Armor[4];
        armors[0] = new Armor(0, "Paçavra", 0, 0);
        armors[1] = new Armor(1, "Normal", 1, 15);
        armors[2] = new Armor(2, "Epic", 3, 25);
        armors[3] = new Armor(3, "Legendary", 5, 40);
        return armors;
    }
    public static Armor getArmorObjByID(int id) {
        for (Armor a : Armor.armors()) {
            if (a.getId() == id)
                return a;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
