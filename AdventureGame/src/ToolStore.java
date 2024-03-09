import javax.lang.model.type.TypeMirror;
import javax.tools.Tool;

public class ToolStore extends NormalLoc {
    private String toolName;
    private Player player;
    private int id;
    private int toolMoney;
    private int damageorBlock;

    public int getDamageorBlock() {
        return damageorBlock;
    }

    void menu() {

    }

    void buy() {

    }

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    public ToolStore(Player player, String toolName, int id, int damageorBlock, int toolMoney) {
        super(player, "Tool Store");
        this.id = id;
        this.toolMoney = toolMoney;
        this.damageorBlock = damageorBlock;
        this.toolName = toolName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToolMoney() {
        return toolMoney;
    }

    public void setToolMoney(int toolMoney) {
        this.toolMoney = toolMoney;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public void setDamageorBlock(int damageorBlock) {
        this.damageorBlock = damageorBlock;
    }

    @Override
    public boolean onLocation() {
        boolean showMenu = true;
        while (showMenu) {
            System.out.println(this.getPlayer().ANSI_BLACK + this.getPlayer().ANSI_BOLD
                    + "----------Welcome to store----------" + this.getPlayer().ANSI_RESET);
            System.out.println("1- Weapons\n2- Armors\n3- Out");
            System.out.print("Your Choose: ");
            int selectCase = input.nextInt();
            System.out.println();
            while (selectCase < 1 && selectCase > 3) {
                System.out.print("Invalid value, choose again: ");
                selectCase = input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    System.out.println("If you want to go out select your own item\n");
                    buyWeapon();
                    break;
                case 2:
                    printArmors();
                    System.out.println("If you want to go out select your own item\n");
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Yine gelin, bekleriz.");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println(
                this.getPlayer().ANSI_BLACK + this.getPlayer().ANSI_BOLD + "----------Weapons----------"
                        + this.getPlayer().ANSI_RESET + "\n");
        int i = 0;
        for (Weapon w : Weapon.weapons()) {
            System.out.println(this.getPlayer().itemsColor[i] + "ID: " + w.getId() +
                    "\tName: " + w.getName() +
                    " \t Damage: " + w.getDamage() +
                    " \t Money: " + w.getMoney() + Player.ANSI_RESET);
            i++;
        }
        System.out.println();

    }

    public void printArmors() {
        int i = 0;
        System.out.println(this.getPlayer().ANSI_BLACK + this.getPlayer().ANSI_BOLD + "----------Armors----------"
                + this.getPlayer().ANSI_RESET + "\n");
        for (Armor w : Armor.armors()) {
            System.out.println(this.getPlayer().itemsColor[i] + "ID: " + w.getId() +
                    "\tName: " + w.getName() +
                    " \t Block: " + w.getBlock() +
                    " \t Money: " + w.getMoney() + Player.ANSI_RESET);
            i++;
        }
    }

    public void buyWeapon() {
        System.out.print("Select a weapon: ");
        int newWeapon = input.nextInt();
        while (newWeapon < 0 || newWeapon > Weapon.weapons().length - 1) {
            System.out.print("Invalid value, try again: ");
            newWeapon = input.nextInt();
        }
        if (this.getPlayer().getMoney() >= Weapon.getWeaponObjByID(newWeapon).getMoney()) {
            if (newWeapon != this.getPlayer().getCurrentWeaponID()) {
                int diffDamage = this.getPlayer().getDefaulDamage() + Weapon.getWeaponObjByID(newWeapon).getDamage();
                int diffMoney = this.getPlayer().getMoney() - Weapon.getWeaponObjByID(newWeapon).getMoney();
                this.getPlayer().setDamage(diffDamage);
                this.getPlayer().setMoney(diffMoney);
                this.getPlayer().setCurrentWeaponID(newWeapon);
                this.getPlayer().myCharacter(); // Karakterinin anlık bilgilerini yazar.
            } else {
                System.out.println("You have this item already.");
            }

        } else {
            System.out.println("You don't hane enough money.");
        }
    }

    public void buyArmor() {

        System.out.print("Select an armor: ");
        int newArmor = input.nextInt();
        while (newArmor < 0 || newArmor > Armor.armors().length - 1) {
            System.out.print("Invalid value, try again: ");
            newArmor = input.nextInt();
        }
        if (this.getPlayer().getMoney() >= Armor.getArmorObjByID(newArmor).getMoney()) {
            if (newArmor != this.getPlayer().getCurrentArmorID()) {
                int diffHealth = this.getPlayer().getDefaultHealth() + Armor.getArmorObjByID(newArmor).getBlock();
                int diffMoney = this.getPlayer().getMoney() - Armor.getArmorObjByID(newArmor).getMoney();
                this.getPlayer().setHealth(diffHealth);
                this.getPlayer().setMoney(diffMoney);
                this.getPlayer().setCurrentArmorID(newArmor);
                this.getPlayer().myCharacter(); // Karakterinin anlık bilgilerini yazar.
            } else {
                System.out.println("You have this item already.");
            }

        } else {
            System.out.println("You don't hane enough money.");
        }
        System.out.println("----------------------------------------------------------------");

    }
}
