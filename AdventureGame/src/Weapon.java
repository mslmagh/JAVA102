public class Weapon {

    private int id;
    private int damage;
    private int money;
    private String name;

    public Weapon(int id, String name, int damage, int money) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.money = money;
    }

    public static Weapon[] weapons() {
        Weapon[] weaponsList = new Weapon[4];
        weaponsList[0] = new Weapon(0, "Yırmık", 0, 0);
        weaponsList[1] = new Weapon(1, "Pistol", 2, 25);
        weaponsList[2] = new Weapon(2, "Sword", 3, 35);
        weaponsList[3] = new Weapon(3, "Rifle", 7, 45);
        return weaponsList;
    }

    public static Weapon getWeaponObjByID(int id) {
        for (Weapon w : Weapon.weapons()) {
            if (w.getId() == id)
                return w;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
