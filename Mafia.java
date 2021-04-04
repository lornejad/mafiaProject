public class Mafia extends Player{
    protected String whoDie;
    protected boolean God = false ;
    private boolean night_vote = false;

    public Mafia(String name) {
        super(name);
        wakeup = true ;
    }

    public boolean isNight_vote() {
        return night_vote;
    }

    public void setNight_vote(){night_vote=true;}

    public void resetWhoDie(){
        whoDie = null;
        night_vote = false;
    }
    @Override
    public String getRole() {
        return "Mafia";
    }

    public String getWhoDie() {
        return whoDie;
    }

    public void setWhoDie(String whoDie) {
        this.whoDie = whoDie;
    }
}
