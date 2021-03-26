public class Detective extends Villager {
    private String who;

    @Override
    public String getRole() {
        return "Detective";
    }

    public Detective(String name) {
        super(name);
        wakeup = true;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
