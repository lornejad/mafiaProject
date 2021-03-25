public class Detective extends villager {
    private String who;

    public Detective(String name) {
        super(name);
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
