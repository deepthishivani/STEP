package abstraction_interface.assignment_problems;

interface Attackable {
    String attack();
    String attack(String weaponName);
}

interface Defendable {
    String defend();
}

abstract class GameCharacter {
    private static int count = 0;
    private final String characterId;

    GameCharacter() {
        characterId = "GC-" + (++count);
    }

    public abstract String getSpecialMove();

    String getCharacterId() {
        return characterId;
    }
}

class Warrior extends GameCharacter implements Attackable, Defendable {
    private String name;

    public Warrior(String name) {
        this.name = name;
    }

    public String attack() {
        return name + " strikes with a blade";
    }

    public String attack(String weaponName) {
        return name + " strikes with an " + weaponName;
    }

    public String defend() {
        return name + " raises a shield";
    }

    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}

class Trap implements Defendable {
    private String trapType;

    public Trap(String trapType) {
        this.trapType = trapType;
    }

    public String defend() {
        return trapType + " triggers automatically";
    }
}

public class Problem4 {
    static void resolveDefense(Defendable[] combatants) {
        for (Defendable d : combatants)
            System.out.println(d.defend());
    }

    public static void main(String[] args) {
        Warrior w = new Warrior("Kael");

        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());

        Trap t = new Trap("Spike Pit");
        System.out.println(t.defend());

        resolveDefense(new Defendable[]{w, t});
    }
}
