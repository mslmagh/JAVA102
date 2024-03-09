import java.util.Scanner;
import javax.swing.InputMap;
import javax.tools.Tool;

public class Player {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_DARK_GRAY = "\u001B[90m";
    public static final String ANSI_BRIGHT_GRAY = "\u001B[37m";

    // Yazı tipi kodları
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String ANSI_ITALIC = "\u001B[3m";

    public static String[] awards = new String[] { " ", "", "", "" };
    private int damage;
    private int health;
    private int defaultHealth;
    private int defaulDamage;
    private int currentWeaponID;
    private int currentArmorID;

    public String[] itemsColor = new String[] { ANSI_GREEN, ANSI_BLUE, ANSI_RED, ANSI_PURPLE };

    public int getCurrentArmorID() {
        return currentArmorID;
    }

    public void setCurrentArmorID(int currentArmorID) {
        this.currentArmorID = currentArmorID;
    }

    public int getCurrentWeaponID() {
        return currentWeaponID;
    }

    public void setCurrentWeaponID(int currentWeaponID) {
        this.currentWeaponID = currentWeaponID;
    }

    public int getDefaulDamage() {
        return defaulDamage;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    private int money;
    private String name;
    private String charName;
    protected Scanner scan = new Scanner(System.in);

    Player(String name) {
        this.name = name;
    }

    void selectChar() {
        Samurai samurai = new Samurai();
        Knight knight = new Knight();
        Archer archer = new Archer();
        Characters[] charList = { samurai, archer, knight };
        System.out.println("----------------------------------------");
        for (Characters characters : charList) {
            System.out.println("ID: " + characters.getId() +
                    "\tCharacter: " + characters.getName() +
                    " \t Damage: " + characters.getDamage() +
                    " \t Health: " + characters.getHealth() +
                    " \t Money: " + characters.getMoney());
        }
        System.out.println("----------------------------------------");
        System.out.println();
        System.out.print("Lütfen bir karakter seçiniz: ");
        int selectChar = scan.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(samurai);
                defaultHealth = samurai.getHealth();
                defaulDamage = samurai.getDamage();
                break;
            case 2:
                initPlayer(archer);
                defaultHealth = archer.getHealth();
                defaulDamage = archer.getDamage();

                break;
            case 3:
                initPlayer(knight);
                defaultHealth = knight.getHealth();
                defaulDamage = knight.getDamage();

                break;
            default:
                initPlayer(samurai);
                defaultHealth = samurai.getHealth();
                defaulDamage = samurai.getDamage();
        }
        myCharacter();
        System.out.println();
    }

    void myCharacter() {
        System.out.println(ANSI_ITALIC + "Your Character: " + this.getCharName() +
                " \t Damage: " + this.getTotalDamage() +
                " \t Health: " + this.getHealth() +
                " \t Money: " + this.getMoney());
    }

    public void initPlayer(Characters characters) {
        this.setDamage(characters.getDamage());
        this.setHealth(characters.getHealth());
        this.setMoney(characters.getMoney());
        this.setCharName(characters.getName());
    }

    public int getTotalDamage() {
        return damage + Weapon.getWeaponObjByID(currentWeaponID).getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int healt) {
        if (healt < 0)
            healt = 0;
        this.health = healt;
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

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

}
