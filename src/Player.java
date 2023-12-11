public class Player {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Die getDie() {
        return die;
    }

    public void setDie() {
        this.die = die;
    }

    private Die die;

    public int getRolledValue() {
        return rolledValue;
    }

    public void setRolledValue(int rolledValue) {
        this.rolledValue = rolledValue;
    }

    private int rolledValue;

    public Player(String name, Die die) {

    }
}