public class Mafia extends Player {
    protected String whoDie;
    protected boolean God = false ;

    public Mafia(String name) {
        super(name);
    }

    public boolean isGod() {
        return God;
    }

    public String getWhoDie() {
        return whoDie;
    }

    public void setWhoDie(String whoDie) {
        this.whoDie = whoDie;
    }
}
