public class Detective extends Villager {
    private String who;
    private boolean detect = false;

    public boolean isDetect() {
        return detect;
    }

    public void Detect() {
        detect = true;
    }

    public void resetDetect(){detect = false;}

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
