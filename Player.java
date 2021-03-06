abstract class Player {
    private String name;
    private int vote_Count = 0;
    protected boolean jocker = false;
    protected boolean silence = false;
    protected boolean wakeup = false;
    protected boolean alive = true ;

    public boolean isWakeup() {
        return wakeup;
    }

    public boolean isSilence() {
        return silence;
    }

    public void setSilence() {
        this.silence = true;
    }

    public void resetSilence(){
        this.silence = false;
    }

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name +"' , "+"role='"+getRole()+ '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void increase_vote_Count(){
        vote_Count++;
    }

    public int getVote_Count() {
        return vote_Count;
    }

    public void resetVote_Count() {
        this.vote_Count = 0;
    }

    public abstract String getRole();

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
