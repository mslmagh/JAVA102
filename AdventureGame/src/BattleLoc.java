public abstract class BattleLoc extends Location {
    private Monster monster;
    private String award;
    private int maxMonster;
    private int monsterNumber;

    public BattleLoc(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        monsterNumber = randomMonsterNumber();
        System.out.println("You are here: " + this.getName());
        System.out.println("Be careful, There are " + monsterNumber + " " + this.monster.getName() + "s here!");
        System.out.print("<E>nter or <R>un: ");
        String selectCase = input.nextLine();
        System.out.println();
        if (selectCase.equalsIgnoreCase("e")) {
            if (combat(monsterNumber)) {
                System.out.println("You won all of the enemies");
            }
        }

        if (this.getPlayer().getHealth() <= 0 && monster.getHealth() > 0) {
            afterHit();
            System.out.println("You died.");
            return false;
        }
        return true;
    }

    public boolean combat(int monsterNumber) {
        for (int i = 1; i <= monsterNumber; i++) {
            int rndm = (int) (Math.random() * 2);
            monster.setHealth(monster.getDefHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.print("<R>un or <F>igth: ");
                String selectedCombat = input.nextLine();
                if (selectedCombat.equalsIgnoreCase("f")) {
                    if (rndm == 0) {
                        System.out.println("You hit to " + monster.getName());
                        monster.setHealth(monster.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (monster.getHealth() > 0) {
                            System.out.println();
                            System.out.println(monster.getName() + " hit you");
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monster.getDamage());
                            afterHit();
                        }
                    } else {
                        System.out.println();
                        System.out.println(monster.getName() + " hit you");
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - monster.getDamage());
                        afterHit();
                        if (this.getPlayer().getHealth() > 0) {
                            System.out.println("You hit to " + monster.getName());
                            monster.setHealth(monster.getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        if (monster.getHealth() <= 0) {
            System.out.println("-------------------------------------");
            System.out.println("You defeated the enemies ");
            System.out.println("you won " + monster.getMoney() + " money");
            this.getPlayer().setMoney(monster.getMoney() + this.getPlayer().getMoney());
            System.out.println("Your current money: " + this.getPlayer().getMoney());
        }

        return false;
    }

    public void afterHit() {
        System.out.println("-----------------------------------------");
        System.out.println("Your health: " + this.getPlayer().getHealth());
        System.out.println(monster.getName() + " health: " + monster.getHealth() +"\n");
    }

    public void playerStats() {
        System.out.println("########Oyuncu değerleri########");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("------------------------------");

    }

    public void monsterStats(int i) {
        System.out.println("######## " + i + "." + this.getMonster().getName() + " Değerleri########");
        System.out.println("Health: " + this.monster.getHealth());
        System.out.println("Damage: " + this.monster.getDamage());
        System.out.println("Money Award: " + this.monster.getMoney());
    }

    public int randomMonsterNumber() {
        int r;
        return r = (int) (Math.random() * maxMonster) + 1;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

}
