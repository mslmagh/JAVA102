/**
 * @autor: Müslüm Agah
 * @since: 07.04.2024
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HW05_20210808042 {
    public static void main(String[] args) throws Exception {

    }

    interface Damageable {
        public void takeDamage(int damage);

        public void takeHealing(int healing);

        public Boolean isAlive();
    }

    interface Caster {
        void castSpell(Damageable target);

        void learnSpell(Spell spell);
    }

    interface Combat extends Damageable {
        void attack(Damageable target);

        void lootWeapon(Weapon weapon);
    }

    interface Useable {
        int use();
    }

    class Spell implements Useable {
        private int minHeal;
        private int maxHeal;
        private String name;

        public Spell(String name, int minHeal, int maxHeal) {
            this.name = name;
            this.minHeal = minHeal;
            this.maxHeal = maxHeal;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private int heal() {
            Random rand = new Random();
            return rand.nextInt(maxHeal - minHeal + 1) + minHeal;
        }

        @Override
        public int use() {
            return heal();
        }
    }

    class Weapon implements Useable {
        private int minDamage;
        private int maxDamage;
        private String name;

        public Weapon(String name, int minDamage, int maxDamage) {
            this.name = name;
            this.minDamage = minDamage;
            this.maxDamage = maxDamage;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private int attack() {
            Random rand = new Random();
            return rand.nextInt(maxDamage - minDamage + 1) + minDamage;
        }

        @Override
        public int use() {
            return attack();
        }
    }

    class Attributes {
        private int strength;
        private int intelligence;

        public Attributes() {
            this.strength = 3;
            this.intelligence = 3;
        }

        public Attributes(int strength, int intelligence) {
            this.strength = strength;
            this.intelligence = intelligence;
        }

        public int getStrength() {
            return strength;
        }

        public void setStrength(int strength) {
            this.strength = strength;
        }

        public int getIntelligence() {
            return intelligence;
        }

        public void setIntelligence(int intelligence) {
            this.intelligence = intelligence;
        }

        public void increaseStrength(int amount) {
            this.strength += amount;
        }

        public void increaseIntelligence(int amount) {
            this.intelligence += amount;
        }

        @Override
        public String toString() {
            return "Attributes [Strength=" + strength + ", intelligence=" + intelligence + "]";
        }
    }

    abstract class Character implements Comparable<Character>, Damageable {
        private String name;
        protected int level;
        protected Attributes attributes;
        protected int health;

        public Character(String name, Attributes attributes) {
            this.name = name;
            this.level = 1;
            this.attributes = attributes;
            this.health = 100;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        @Override
        public Boolean isAlive() {
            if (health > 0) {
                return true;
            }
            return null;
        }

        public int compareTo(Character other) {
            return Integer.compare(this.level, other.level);
        }

        public abstract void levelUp();

        @Override
        public String toString() {
            return getClass().getSimpleName() + " LvL: " + level + " – " + attributes.toString();
        }
    }

    abstract class PlayableCharacter extends Character {
        private boolean inParty;
        private Party party;

        public PlayableCharacter(String name, Attributes attributes) {
            super(name, attributes);
        }

        public boolean isInParty() {
            return inParty;
        }

       
        public void joinParty(Party party) throws AlreadyInPartyException, PartyLimitReachedException {
            if (inParty) {
                throw new AlreadyInPartyException("Character " + super.getName() + " is already in a party.");
            }

            this.party = party;
            party.addCharacter(this);
            inParty = true;
        }

        public void quitParty() throws CharacterIsNotInPartyException {
            if (!inParty) {
                throw new CharacterIsNotInPartyException("Character " + super.getName() + " is not in any party.");
            }

            party.removeCharacter(this);
            party = null;
            inParty = false;
        }
    }

    abstract class NonPlayableCharacter extends Character {
        public NonPlayableCharacter(String name, Attributes attributes) {
            super(name, attributes);
        }
    }

    class Merchant extends NonPlayableCharacter {
        private ArrayList<Useable> inventory;

        public Merchant(String name) {
            super(name, new Attributes(0, 0));
            inventory = new ArrayList<>();
        }

        @Override
        public void levelUp() {
        }

        public void display() {
            for (Useable item : inventory) {
                System.out.println(item);
            }
        }

        public Useable buy(int itemNumber) throws ItemNotFoundException {
            if (itemNumber < 0 || itemNumber >= inventory.size()) {
                throw new ItemNotFoundException("Item with number " + itemNumber + " not found.");
            }
            return inventory.get(itemNumber);
        }

        public void sell(Useable item) {
            inventory.add(item);
        }

        public ArrayList<Useable> getInventory() {
            return inventory;
        }

        @Override
        public void takeDamage(int damage) {
            System.out.println("Merchant cannot take damage.");
        }

        @Override
        public void takeHealing(int healing) {
            System.out.println("Merchant cannot take healing.");
        }
    }

    class Skeleton extends NonPlayableCharacter implements Combat {
        public Skeleton(String name, Attributes attributes) {
            super(name, attributes);
        }

        @Override
        public void levelUp() {
            this.attributes.increaseStrength(1);
            this.attributes.increaseIntelligence(1);
            this.level++;
        }

        @Override
        public void takeHealing(int healing) {
            takeDamage(healing);
        }

        @Override
        public void attack(Damageable target) {
            int totalAttributes = attributes.getStrength() + attributes.getIntelligence();
            target.takeDamage(totalAttributes);
        }

        @Override
        public void lootWeapon(Weapon weapon) {

        }

        @Override
        public void takeDamage(int damage) {
            this.health -= damage;
            if (this.health < 0) {
                this.health = 0;
            }
        }
    }

    class Warrior extends PlayableCharacter implements Combat {
        private Useable weapon;

        public Warrior(String name) {
            super(name, new Attributes(4, 2));
            this.health = 35;
        }

        @Override
        public void levelUp() {
            this.attributes.increaseStrength(2);
            this.attributes.increaseIntelligence(0);
            this.level++;
        }

        @Override
        public void attack(Damageable target) {
            int totalDamage = this.attributes.getStrength() + this.weapon.use();
            target.takeDamage(totalDamage);
        }

        @Override
        public void lootWeapon(Weapon weapon) {
            this.weapon = weapon;
        }

        @Override
        public void takeDamage(int damage) {
            this.health -= damage;
            if (this.health < 0) {
                this.health = 0;
            }
        }

        @Override
        public void takeHealing(int healing) {
            this.health += healing;
            if (this.health > 35) {
                this.health = 35;
            }
        }
    }

    class Cleric extends PlayableCharacter implements Caster {
        private Useable spell;

        public Cleric(String name) {
            super(name, new Attributes(2, 4));
            this.health = 25;
        }

        @Override
        public void levelUp() {
            this.attributes.increaseStrength(1);
            this.attributes.increaseIntelligence(2);
            this.level++;
        }

        @Override
        public void castSpell(Damageable target) {
            int healing = this.attributes.getIntelligence() + this.spell.use();
            target.takeHealing(healing);
        }

        @Override
        public void learnSpell(Spell spell) {
            this.spell = spell;
        }

        @Override
        public void takeDamage(int damage) {
            this.health -= damage;
            if (this.health < 0) {
                this.health = 0;
            }
        }

        @Override
        public void takeHealing(int healing) {
            this.health += healing;
            if (this.health > 25) {
                this.health = 25;
            }
        }
    }

    class Paladin extends PlayableCharacter implements Combat, Caster {
        private Useable weapon;
        private Useable spell;

        public Paladin(String name) {
            super(name, new Attributes());
            this.health = 30;
        }

        @Override
        public void levelUp() {
            if (level % 2 == 0) {
                this.attributes.increaseStrength(2);
                this.attributes.increaseIntelligence(1);
            } else {
                this.attributes.increaseStrength(1);
                this.attributes.increaseIntelligence(2);
            }
            this.level++;
        }

        @Override
        public void attack(Damageable target) {
            int totalDamage = this.attributes.getStrength() + this.weapon.use();
            target.takeDamage(totalDamage);
        }

        @Override
        public void lootWeapon(Weapon weapon) {
            this.weapon = weapon;
        }

        @Override
        public void castSpell(Damageable target) {
            int healing = this.attributes.getIntelligence() + this.spell.use();
            target.takeHealing(healing);
        }

        @Override
        public void learnSpell(Spell spell) {
            this.spell = spell;
        }

        @Override
        public void takeDamage(int damage) {
            this.health -= damage;
            if (this.health < 0) {
                this.health = 0;
            }
        }

        @Override
        public void takeHealing(int healing) {
            this.health += healing;
            if (this.health > 30) {
                this.health = 30;
            }
        }
    }

    class Party {
        private static final int PARTY_LIMIT = 8;
        private Collection<Combat> fighters;
        private Collection<Caster> healers;
        private int mixedCount;

        public Party() {
            fighters = new ArrayList<>();
            healers = new ArrayList<>();
            mixedCount = 0;
        }

        public void addCharacter(PlayableCharacter character)
                throws PartyLimitReachedException, AlreadyInPartyException {
            if (fighters.size() + healers.size() >= PARTY_LIMIT) {
                throw new PartyLimitReachedException("Party limit reached. Cannot add character to party.");
            }

            if (character instanceof Combat && fighters.contains(character)) {
                throw new AlreadyInPartyException("Character " + character.getName() + " is already in the party.");
            }

            if (character instanceof Caster && healers.contains(character)) {
                throw new AlreadyInPartyException("Character " + character.getName() + " is already in the party.");
            }

            if (character instanceof Combat) {
                fighters.add((Combat) character);
            }

            if (character instanceof Caster) {
                healers.add((Caster) character);
                if (character instanceof Paladin) {
                    mixedCount++;
                }
            }
        }

        public void removeCharacter(PlayableCharacter character) throws CharacterIsNotInPartyException {
            if (character instanceof Combat && !fighters.contains(character)) {
                throw new CharacterIsNotInPartyException("Fighter " + character.getName() + " is not in the party.");
            }

            if (character instanceof Caster && !healers.contains(character)) {
                throw new CharacterIsNotInPartyException("Healer " + character.getName() + " is not in the party.");
            }

            if (character instanceof Combat) {
                fighters.remove(character);
            }

            if (character instanceof Caster) {
                healers.remove(character);
                if (character instanceof Paladin) {
                    mixedCount--;
                }
            }
        }

        public void partyLevelUp() {
            for (PlayableCharacter character : getPlayableCharacters()) {
                if (!(character instanceof Paladin) || (character.getLevel() % 2 == 0)) {
                    character.levelUp();
                }
            }

            for (PlayableCharacter character : getPlayableCharacters()) {
                if (character instanceof Paladin && (character.getLevel() % 2 != 0)) {
                    character.levelUp();
                }
            }
        }

        private boolean isCharacterInParty(PlayableCharacter character) {
            return fighters.contains(character) || healers.contains(character);
        }

        private boolean isPartyFull() {
            return fighters.size() + healers.size() + mixedCount >= PARTY_LIMIT;
        }

        private Collection<PlayableCharacter> getPlayableCharacters() {
            Collection<PlayableCharacter> characters = new ArrayList<>();
            characters.addAll((Collection<? extends PlayableCharacter>) fighters);
            characters.addAll((Collection<? extends PlayableCharacter>) healers);
            return characters;
        }

        @Override
        public String toString() {
            ArrayList<PlayableCharacter> characters = new ArrayList<>();
            characters.addAll((Collection<? extends PlayableCharacter>) fighters);
            characters.addAll((Collection<? extends PlayableCharacter>) healers);
            Collections.sort(characters);

            StringBuilder builder = new StringBuilder();
            for (PlayableCharacter character : characters) {
                builder.append(character).append("\n");
            }
            return builder.toString();
        }

        public int getMixedCount() {
            return mixedCount;
        }

        public Collection<Combat> getFighters() {
            return fighters;
        }

        public Collection<Caster> getHealers() {
            return healers;
        }

        public static int getPartyLimit() {
            return PARTY_LIMIT;
        }
    }

    class PartyLimitReachedException extends Exception {
        public PartyLimitReachedException(String message) {
            super(message);
        }
    }

    class AlreadyInPartyException extends Exception {
        public AlreadyInPartyException(String message) {
            super(message);
        }
    }

    class CharacterIsNotInPartyException extends Exception {
        public CharacterIsNotInPartyException(String message) {
            super(message);
        }
    }

    class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String message) {
            super(message);
        }
    }
}
